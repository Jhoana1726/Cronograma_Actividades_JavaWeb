package Usuario;

import conexion.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class eliminarUsuario {

    public static void main(String[] args) {
        conexion con = new conexion();
        Connection cn;
        PreparedStatement ps;
        Statement st;
        ResultSet rs;

        // Dato a eliminarUsuario 
        int id_eliminar = 7;

        try {
           
            Class.forName("com.mysql.cj.jdbc.Driver");

            cn = con.getConnection(); 
            if (cn == null) {
                System.out.println("No se pudo conectar a la base de datos.");
                return;
            }

            //ELIMINAR registro
            String sql = "DELETE FROM Usuario WHERE id_usuario = ?";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, id_eliminar);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Registro con id " + id_eliminar + " eliminado correctamente.");
            } else {
                System.out.println(" No se encontró el registro con id " + id_eliminar);
            }

            // MOSTRAR los registros restantes
            st = cn.createStatement();
            rs = st.executeQuery("SELECT * FROM Usuario");

            System.out.println("Registros actuales en la tabla:");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id_usuario") + ": "
                        + rs.getString("nombre") + " - "
                        + rs.getString("email") + " - "
                        + rs.getString("celular") + " - "
                        + rs.getString("genero") + " - "
                        + rs.getInt("edad") + " - "
                        + rs.getString("cedula") + " - "
                        + rs.getString("barrio") + " - "
                        + rs.getInt("estrato")
                );
            }

        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(eliminarUsuario.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
