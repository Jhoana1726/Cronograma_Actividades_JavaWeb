package Usuario;

import conexion.conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class consultarUsuario {
    public static void main(String[] args) {
        conexion con = new conexion();
        Connection cn;
        Statement st;
        ResultSet rs;

        try {
            cn = con.getConnection();
            if (cn == null) {
                System.out.println("No se pudo establecer conexión con la base de datos");
                return;
            }

            st = cn.createStatement();
            rs = st.executeQuery("SELECT * FROM Usuario"); // where id_usuario = 14

            // Recorrer los resultados
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

        } catch (SQLException ex) {
            Logger.getLogger(consultarUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
