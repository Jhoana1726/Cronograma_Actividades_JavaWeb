    package Usuario;

    import org.junit.jupiter.api.*;
    import static org.junit.jupiter.api.Assertions.*;
    import Usuario.modificarUsuario.Genero;
    
    /**
     * Clase de pruebas para verificar el correcto funcionamiento
     * del método modificarUsuario() de la clase modificarUsuario.
     * 
     * Cada prueba evalúa un escenario distinto (válido, duplicado y no existente).
     */
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    public class ModificarUsuarioTest {

        // Instancia de la clase que se va a probar
        private static modificarUsuario modificar;

        @BeforeAll
        static void setUp() {
            // Se crea una sola instancia antes de ejecutar todas las pruebas
            modificar = new modificarUsuario();
            System.out.println("Conexión lista. Iniciando pruebas de modificación de usuario...");
        }

        /**
         * Caso 1: Modificar un usuario existente con datos válidos.
         * Debe retornar true porque el ID existe y el correo no está duplicado.
         */
        @Test
        @Order(1)
        void testModificarUsuarioValido() {
            boolean resultado = modificar.modificarUsuarioBD(
                2, // Este ID debe existir en tu base de datos
                "Laura Sánchez",
                "alexgironza@example.com",
                "3007654321",
                Genero.F,
                35,
                "1002789345",
                "La Floresta",
                3
            );
            assertTrue(resultado, "La actualización debería realizarse correctamente para un usuario válido.");
        }

        /**
         * Caso 2: Intentar modificar usando un correo que ya pertenece a otro usuario.
         * Debe retornar false porque no se puede duplicar el email.
         */
        @Test
        @Order(2)
        void testModificarUsuarioConCorreoDuplicado() {
            boolean resultado = modificar.modificarUsuarioBD(
                2, // Usuario existente
                "Laura Sánchez",
                "isa2323@gmail.com", // Este correo debe existir en otro usuario de la tabla
                "3012223344",
                Genero.F,
                35,
                "1002789345",
                "El Porvenir",
                3
            );
            assertFalse(resultado, "No debería permitir actualizar con un correo ya existente.");
        }

        /**
         * Caso 3: Intentar modificar un usuario que no existe.
         * Debe retornar false porque el ID no está registrado en la base de datos.
         */
        @Test
        @Order(3)
        void testModificarUsuarioNoExistente() {
            boolean resultado = modificar.modificarUsuarioBD(
                9999, // ID que no existe en la base de datos
                "Usuario Inexistente",
                "nuevoinexistente@dominio.com",
                "3010000000",
                Genero.M,
                40,
                "1000000000",
                "Bello Horizonte",
                1
            );
            assertFalse(resultado, "No debería modificar porque el usuario con ese ID no existe.");
        }
    }
