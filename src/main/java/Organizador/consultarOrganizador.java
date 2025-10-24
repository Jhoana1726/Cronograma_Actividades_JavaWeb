package Organizador;

import conexion.conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class consultarOrganizador {
    public static void main(String[] args) {
        conexion con = new conexion();
        Connection cn;
        Statement st;
        ResultSet rs;

        try {
            cn = con.getConnection();
            st = cn.createStatement();

            String sql = "SELECT o.id_organizador, u.nombre " +
                         "FROM Organizador o " +
                         "JOIN Usuario u ON o.id_organizador = u.id_usuario";

            rs = st.executeQuery(sql);

            System.out.println("Organizadores:");
            while (rs.next()) {
                System.out.println(
                    "ID Organizador: " + rs.getInt("id_organizador") +
                    " | Nombre: " + rs.getString("nombre")
                );
            }

        } catch (Exception e) {
            Logger.getLogger(consultarOrganizador.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
