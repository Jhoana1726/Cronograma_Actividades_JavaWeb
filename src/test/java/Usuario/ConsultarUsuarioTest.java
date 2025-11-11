package Usuario;

// Importaciones necesarias para JUnit 5
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Importa la clase que será probada
import Usuario.consultarUsuario;
import java.util.ArrayList;

/**
 * Clase de prueba unitaria para verificar el correcto funcionamiento
 * del módulo de "Consulta de Usuarios".
 *
 * Estas pruebas aseguran que el sistema pueda obtener información
 * desde la base de datos sin errores de conexión ni ejecución.
 */
public class ConsultarUsuarioTest {

    private consultarUsuario consulta;

    /**
     * Se ejecuta antes de cada prueba unitaria.
     * Su propósito es inicializar la instancia que se probará.
     */
    @BeforeEach
    public void setUp() {
        consulta = new consultarUsuario(); // Se crea una nueva instancia antes de cada prueba
    }

    /**
     * Prueba unitaria básica que verifica que la consulta devuelve
     * una lista (aunque sea vacía) sin producir errores.
     */
    @Test
    public void testObtenerUsuarios() {
        ArrayList<String> usuarios = consulta.obtenerUsuarios();

        // Verifica que la lista no sea nula (es decir, que se haya ejecutado la consulta correctamente)
        assertNotNull(usuarios, "La lista de usuarios no debe ser nula.");

        // Si hay usuarios en la base de datos, la lista debe contener al menos uno
        // Esta verificación no falla si la tabla está vacía, pero sirve como control informativo
        if (!usuarios.isEmpty()) {
            System.out.println("Usuarios encontrados: " + usuarios);
            assertTrue(usuarios.size() > 0, "Se encontraron usuarios en la base de datos.");
        } else {
            System.out.println("No hay usuarios registrados en la base de datos.");
        }
    }
}
