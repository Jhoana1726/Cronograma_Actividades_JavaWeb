package Presidente_Junta;

import conexion.conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class consultarPresidente {

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
            rs = st.executeQuery("SELECT pj.id_presidente, u.nombre, pj.periodo, pj.activo FROM Presidente_Junta pj JOIN Usuario u ON pj.id_presidente = u.id_usuario");

            System.out.println("Presidentes:");
            while (rs.next()) {
                System.out.println(
                    rs.getInt("id_presidente") + " : "
                    + rs.getString("nombre") + " : "
                    + rs.getString("periodo") + " : "
                    + (rs.getBoolean("activo") ? "Activo" : "Inactivo"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(consultarPresidente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
