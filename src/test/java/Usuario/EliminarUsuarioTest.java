package Usuario;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas unitarias para el método eliminarUsuarioBD()
 * de la clase eliminarUsuario.
 * 
 * Se prueban tres escenarios:
 * 1. Eliminación válida
 * 2. Eliminación repetida (usuario ya eliminado)
 * 3. Eliminación de usuario inexistente
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EliminarUsuarioTest {

    private static eliminarUsuario eliminar;

    @BeforeAll
    static void setUp() {
        eliminar = new eliminarUsuario();
        System.out.println("Conexión lista. Iniciando pruebas de eliminación de usuario...");
    }

    /**
     * Caso 1: Eliminar un usuario existente.
     * Debe retornar true porque el ID existe en la base de datos.
     */
    @Test
    @Order(1)
    void testEliminarUsuarioExistente() {
        boolean resultado = eliminar.eliminarUsuarioBD(10); 
        assertTrue(resultado, "Debería eliminar correctamente al usuario existente.");
    }

    /**
     * Caso 2: Intentar eliminar un usuario ya eliminado.
     * Debe retornar false porque ya no existe en la base de datos.
     */
    @Test
    @Order(2)
    void testEliminarUsuarioYaEliminado() {
        boolean resultado = eliminar.eliminarUsuarioBD(10); // Mismo ID del anterior
        assertFalse(resultado, "No debería eliminar nuevamente un usuario ya eliminado.");
    }

    /**
     * Caso 3: Intentar eliminar un usuario inexistente.
     * Debe retornar false porque el ID no está registrado.
     */
    @Test
    @Order(3)
    void testEliminarUsuarioNoExistente() {
        boolean resultado = eliminar.eliminarUsuarioBD(9999); // ID que no existe
        assertFalse(resultado, "No debería eliminar porque el usuario con ese ID no existe.");
    }
}
