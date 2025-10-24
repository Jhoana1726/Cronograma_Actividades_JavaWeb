package Asistencia;

import conexion.conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class consultarAsistencia {
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
            rs = st.executeQuery(
                "SELECT a.id_asistencia, e.titulo AS nombre_evento, u.nombre AS nombre_usuario, a.confirmo, a.asistio " +
                "FROM Asistencia a " +
                "JOIN Evento e ON a.id_evento = e.id_evento " +
                "JOIN Usuario u ON a.id_usuario = u.id_usuario"
            );

            while (rs.next()) {
                System.out.println(
                    rs.getInt("id_asistencia") + ": "
                    + rs.getString("nombre_evento") + " - "
                    + rs.getString("nombre_usuario") + " -  Confirmo: "
                    + (rs.getBoolean("confirmo") ? "Sí" : "No") + " - Asistio: "
                    + (rs.getBoolean("asistio") ? "Sí" : "No")
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(consultarAsistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
