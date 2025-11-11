package Usuario;

import conexion.conexion;    // Importa la clase encargada de manejar la conexión con la base de datos.
import java.sql.*;           // Importa las clases necesarias para trabajar con SQL.
import java.util.ArrayList;  // Permite almacenar los resultados en una lista.

/**
 * Clase consultarUsuario
 * Su propósito es obtener y mostrar la lista de usuarios almacenados en la base de datos.
 * 
 * Este tipo de clase forma parte del backend y facilita el acceso a la información 
 * de los usuarios mediante consultas SQL seguras.
 */
public class consultarUsuario {

    /**
     * Objeto que maneja la conexión con la base de datos.
     * Se reutiliza la clase 'conexion' para establecer el enlace.
     */
    private conexion con = new conexion();

    /**
     * Método que consulta todos los usuarios registrados en la base de datos.
     * @return Una lista con los nombres de los usuarios o una lista vacía si ocurre un error.
     * 
     * Este método utiliza un Statement simple porque no se reciben parámetros.
     * En consultas más complejas se debe usar PreparedStatement.
     */
    public ArrayList<String> obtenerUsuarios() {
        ArrayList<String> listaUsuarios = new ArrayList<>(); // Lista donde se almacenarán los resultados
        String sql = "SELECT nombre FROM Usuario";            // Consulta SQL para obtener los nombres de los usuarios

        try (
            Connection cn = con.getConnection();              // Se obtiene la conexión
            Statement st = cn.createStatement();              // Se crea un Statement para ejecutar la consulta
            ResultSet rs = st.executeQuery(sql)               // Se ejecuta la consulta y se guarda el resultado
        ) {
            // Recorre cada fila del resultado y agrega los nombres a la lista
            while (rs.next()) {
                listaUsuarios.add(rs.getString("nombre"));
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Muestra el error en la consola si ocurre una excepción
        }

        // Devuelve la lista (puede estar vacía si no se encontraron usuarios o hubo un error)
        return listaUsuarios;
    }
}
