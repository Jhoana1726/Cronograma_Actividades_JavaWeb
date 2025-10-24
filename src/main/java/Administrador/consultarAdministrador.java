package Administrador;

import conexion.conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class consultarAdministrador {
    public static void main(String[] args) {
        conexion con = new conexion();
        Connection cn;
        Statement st;
        ResultSet rs;

        try {
            cn = con.getConnection();
            st = cn.createStatement();

            String sql = "SELECT a.id_admin, u.nombre, u.email, u.celular FROM Administrador a JOIN Usuario u ON a.id_admin = u.id_usuario";
            rs = st.executeQuery(sql);

            System.out.println("Administradores:");
            while (rs.next()) {
                System.out.println(
                    "ID Admin: " + rs.getInt("id_admin") +
                    " | Nombre: " + rs.getString("nombre") +
                    " | Correo: " + rs.getString("email") +
                    " | Celular: " + rs.getString("celular")
                );
            }

        } catch (Exception e) {
            Logger.getLogger(consultarAdministrador.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
