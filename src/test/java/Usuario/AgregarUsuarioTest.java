package Usuario;

// Importa las clases necesarias para usar JUnit 5
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Importa la clase que se desea probar
import Usuario.agregarUsuario;

/**
 * Clase de prueba unitaria para verificar el correcto funcionamiento
 * del módulo de "Agregar Usuario".
 *
 * Esta clase utiliza JUnit 5 y permite validar tanto casos exitosos
 * como escenarios donde se espera un fallo (por ejemplo, un correo repetido).
 */
public class AgregarUsuarioTest {

    /**
     * Objeto que representa la clase que se desea probar.
     * Se crea una nueva instancia antes de cada prueba.
     */
    private agregarUsuario agregar;

    /**
     * Este método se ejecuta antes de cada prueba.
     * Se utiliza para inicializar los objetos necesarios y
     * garantizar que cada test sea independiente.
     */
    @BeforeEach
    public void setUp() {
        agregar = new agregarUsuario(); 
    }

    /**
     * Prueba unitaria para verificar que un nuevo usuario
     * se pueda registrar correctamente en la base de datos.
     * 
     * Esta prueba supone que el correo 
     * no existe previamente en la base de datos.
     */
    @Test
    public void testAgregarUsuario() {
        String nombre = "Prueba JUnit";
        String email = "pruebaJUnit@gmail.com";
        String password = "12345";
        String celular = "3100000000";
        agregarUsuario.Genero genero = agregarUsuario.Genero.F;
        int edad = 25;
        String cedula = "1234567890";
        String barrio = "Centro";
        int estrato = 3;

        // Llamada al método real que inserta el usuario
        boolean resultado = agregar.insertarUsuario(nombre, email, password, celular, genero, edad, cedula, barrio, estrato);

        // Se verifica que el resultado sea true, indicando éxito en la inserción
        assertTrue(resultado, "El usuario debería haberse agregado correctamente.");
    }

    /**
     * Prueba unitaria que simula el intento de registrar un usuario
     * con un correo electrónico que ya está registrado.
     * 
     * El resultado esperado es false, ya que no se debe permitir
     * insertar correos duplicados.
     */
    @Test
    public void testAgregarUsuarioCorreoExistente() {
        String nombre = "Usuario Existente";
        String email = "isa2323@gmail.com"; // Correo ya existente en la base de datos
        String password = "98765";
        String celular = "3000000000";
        agregarUsuario.Genero genero = agregarUsuario.Genero.M;
        int edad = 28;
        String cedula = "55555555";
        String barrio = "La Floresta";
        int estrato = 4;

        boolean resultado = agregar.insertarUsuario(nombre, email, password, celular, genero, edad, cedula, barrio, estrato);

        // Se espera que el resultado sea false, indicando que el correo ya está registrado
        assertFalse(resultado, "El registro debe fallar si el correo ya existe.");
    }
}
