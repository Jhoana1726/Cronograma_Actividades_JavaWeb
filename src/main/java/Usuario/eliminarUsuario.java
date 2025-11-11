package Usuario;

import conexion.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase eliminarUsuario
 * Permite eliminar un usuario existente en la base de datos
 * y validar si la eliminación fue exitosa.
 */
public class eliminarUsuario {

    private conexion con = new conexion();

    /**
     * Método principal de negocio para eliminar un usuario.
     * 
     * @param id_usuario ID del usuario que se desea eliminar
     * @return true si se eliminó correctamente, false si no existe o ocurre un error
     */
    public boolean eliminarUsuarioBD(int id_usuario) {

        Connection cn = null;
        PreparedStatement ps = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = con.getConnection();

            if (cn == null) {
                System.out.println("No se pudo conectar a la base de datos.");
                return false;
            }

            // Eliminar registro
            String sql = "DELETE FROM Usuario WHERE id_usuario = ?";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, id_usuario);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Registro con id " + id_usuario + " eliminado correctamente.");
                return true;
            } else {
                System.out.println("No se encontró el registro con id " + id_usuario);
                return false;
            }

        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(eliminarUsuario.class.getName()).log(Level.SEVERE, null, e);
            return false;

        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException ignored) {}
            try { if (ps != null) ps.close(); } catch (SQLException ignored) {}
            try { if (st != null) st.close(); } catch (SQLException ignored) {}
            try { if (cn != null) cn.close(); } catch (SQLException ignored) {}
        }
    }
}
