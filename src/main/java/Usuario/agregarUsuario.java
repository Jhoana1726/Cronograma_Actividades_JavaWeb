package Usuario;

import conexion.conexion;     // Importa la clase que gestiona la conexión con la base de datos.
import java.sql.*;             // Importa las clases necesarias para manejar SQL (Connection, PreparedStatement, ResultSet, etc.).

/**
 * Clase agregarUsuario
 * Su propósito es permitir registrar nuevos usuarios en la base de datos y verificar
 * si un correo electrónico ya se encuentra registrado.
 * 
 * Se implementan métodos independientes (sin main) para que puedan ser probados 
 * fácilmente mediante pruebas unitarias con JUnit 5.
 */
public class agregarUsuario {

    /**
     * Enumeración interna para representar los posibles géneros de un usuario.
     * Se utiliza un enum en lugar de texto libre para evitar errores tipográficos
     * y estandarizar los valores posibles.
     */
    public enum Genero {
        F, M, Otro
    }

    /**
     * Objeto de tipo 'conexion' que maneja la conexión a la base de datos.
     * Esto permite obtener un objeto Connection cuando sea necesario.
     */
    private conexion con = new conexion();

    /**
     * Método que verifica si un correo electrónico ya existe en la base de datos.
     * @param email El correo que se desea verificar.
     * @return true si el correo existe, false si no existe o si ocurre un error.
     * 
     * Se utiliza PreparedStatement para prevenir inyección SQL y manejar 
     * parámetros de forma más segura.
     */
    public boolean existeEmail(String email) {
        String sql = "SELECT * FROM Usuario WHERE email = ?";
        try (
            Connection cn = con.getConnection();               
            PreparedStatement ps = cn.prepareStatement(sql)    
        ) {
            ps.setString(1, email);                            
            ResultSet rs = ps.executeQuery();                  
            return rs.next();                                  
        } catch (SQLException e) {
            e.printStackTrace();                               
            return false;                                      
        }
    }

    /**
     * Método que inserta un nuevo usuario en la base de datos.
     * @param nombre Nombre del usuario.
     * @param email Correo electrónico del usuario.
     * @param password Contraseña del usuario.
     * @param celular Número de celular.
     * @param genero Género (F, M u Otro).
     * @param edad Edad del usuario.
     * @param cedula Documento de identidad.
     * @param barrio Barrio de residencia.
     * @param estrato Estrato socioeconómico.
     * @return true si el usuario fue insertado exitosamente, false si ya existe o si ocurre un error.
     * 
     * Este método primero verifica si el correo ya está registrado usando el método 'existeEmail()'.
     * Si no existe, procede a insertar el nuevo usuario con un PreparedStatement.
     */
    public boolean insertarUsuario(String nombre, String email, String password, String celular,
                                   Genero genero, int edad, String cedula, String barrio, int estrato) {

        if (existeEmail(email)) {
            // Si el correo ya existe, no se realiza el registro.
            return false;
        }

        String sql = "INSERT INTO Usuario (nombre, email, password, celular, genero, edad, cedula, barrio, estrato) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (
            Connection cn = con.getConnection();               
            PreparedStatement ps = cn.prepareStatement(sql)    
        ) {
            ps.setString(1, nombre);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, celular);
            ps.setString(5, genero.name()); 
            ps.setInt(6, edad);
            ps.setString(7, cedula);
            ps.setString(8, barrio);
            ps.setInt(9, estrato);

            ps.executeUpdate();

            // Si la inserción fue exitosa, se devuelve true.
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
