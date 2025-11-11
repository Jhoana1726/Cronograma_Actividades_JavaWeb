package Usuario;

import conexion.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase modificarUsuario
 * Permite modificar los datos de un usuario existente en la base de datos.
 * 
 * Este código está adaptado para poder ser probado con JUnit5.
 * No usa método main, sino que expone el método público modificarUsuarioBD(...)
 * que puede ser llamado desde una prueba o desde otro módulo del sistema.
 */
public class modificarUsuario {

    /**
     * Enumeración para controlar los valores válidos del campo "género".
     * Solo permite "M" (masculino) o "F" (femenino).
     */
    public enum Genero {
        M("M"),
        F("F");

        private final String valor;

        Genero(String valor) {
            this.valor = valor;
        }

        public String getValor() {
            return valor;
        }
    }

    // Instancia de la clase conexion, encargada de abrir la conexión a la base de datos
    private conexion con = new conexion();

    /**
     * Método principal de negocio para modificar un usuario.
     * 
     * @param id_usuario  ID del usuario que se desea modificar
     * @param nombre      Nuevo nombre
     * @param email       Nuevo correo electrónico
     * @param celular     Nuevo número de celular
     * @param genero      Nuevo género (enum Genero)
     * @param edad        Nueva edad
     * @param cedula      Nueva cédula
     * @param barrio      Nuevo barrio
     * @param estrato     Nuevo estrato
     * @return true si la actualización fue exitosa, false en caso contrario
     */
    public boolean modificarUsuarioBD(int id_usuario, String nombre, String email, String celular,
                                      Genero genero, int edad, String cedula, String barrio, int estrato) {

        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Establecer conexión con la base de datos
            cn = con.getConnection();
            if (cn == null) {
                System.out.println("No se pudo conectar a la base de datos.");
                return false;
            }

            // Paso 1: verificar que el usuario con ese ID exista
            String sqlExiste = "SELECT id_usuario FROM Usuario WHERE id_usuario = ?";
            ps = cn.prepareStatement(sqlExiste);
            ps.setInt(1, id_usuario);
            rs = ps.executeQuery();

            if (!rs.next()) {
                // No se encontró un usuario con ese ID
                System.out.println("No existe un usuario con el id " + id_usuario);
                return false;
            }

            rs.close();
            ps.close();

            // Paso 2: verificar que el nuevo correo no esté en uso por otro usuario
            String sqlEmail = "SELECT id_usuario FROM Usuario WHERE email = ? AND id_usuario <> ?";
            ps = cn.prepareStatement(sqlEmail);
            ps.setString(1, email);
            ps.setInt(2, id_usuario);
            rs = ps.executeQuery();

            if (rs.next()) {
                // Existe otro usuario con el mismo correo
                System.out.println("Error: el correo '" + email + "' ya está en uso.");
                return false;
            }

            rs.close();
            ps.close();

            // Paso 3: realizar la actualización en la tabla Usuario
            String sqlUpdate = "UPDATE Usuario SET nombre = ?, email = ?, celular = ?, genero = ?, edad = ?, " +
                               "cedula = ?, barrio = ?, estrato = ? WHERE id_usuario = ?";
            ps = cn.prepareStatement(sqlUpdate);
            ps.setString(1, nombre);
            ps.setString(2, email);
            ps.setString(3, celular);
            ps.setString(4, genero.getValor());
            ps.setInt(5, edad);
            ps.setString(6, cedula);
            ps.setString(7, barrio);
            ps.setInt(8, estrato);
            ps.setInt(9, id_usuario);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                // Actualización exitosa
                System.out.println("Usuario con ID " + id_usuario + " actualizado correctamente.");
                return true;
            } else {
                // No se actualizó ningún registro
                System.out.println("No se pudo actualizar el usuario con ID " + id_usuario);
                return false;
            }

        } catch (SQLException e) {
            // Captura y registro del error SQL
            Logger.getLogger(modificarUsuario.class.getName()).log(Level.SEVERE, null, e);
            return false;

        } finally {
            // Cierre ordenado de recursos
            try { if (rs != null) rs.close(); } catch (SQLException ignored) {}
            try { if (ps != null) ps.close(); } catch (SQLException ignored) {}
            try { if (cn != null) cn.close(); } catch (SQLException ignored) {}
        }
    }
}
