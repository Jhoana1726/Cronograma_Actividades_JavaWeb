package Solicitud_Eventos;

import conexion.conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class consultarSolicitud {

    public static void main(String[] args) {
        conexion con = new conexion();
        Connection cn;
        Statement st;
        ResultSet rs;

        try {
            cn = con.getConnection();
            if (cn == null) {
                System.out.println("No se pudo conectar a la base de datos");
                return;
            }

            st = cn.createStatement();

            // Consulta con nombres claros de columnas
            String sql = "SELECT se.id_solicitud AS 'ID Solicitud', " +
                         "e.titulo AS 'Nombre del Evento', " +
                         "u.nombre AS 'Presidente', " +
                         "se.fecha_solicitud AS 'Fecha de Solicitud', " +
                         "se.estado AS 'Estado' " +
                         "FROM Solicitud_Eventos se " +
                         "JOIN Evento e ON se.id_evento = e.id_evento " +
                         "JOIN Presidente_Junta pj ON se.id_presidente = pj.id_presidente " +
                         "JOIN Usuario u ON pj.id_presidente = u.id_usuario";

            rs = st.executeQuery(sql);

            System.out.println("Solicitudes:");
            while (rs.next()) {
                System.out.println(
                        "ID Solicitud: " + rs.getInt("ID Solicitud") + " | " +
                        "Evento: " + rs.getString("Nombre del Evento") + " | " +
                        "Presidente: " + rs.getString("Presidente") + " | " +
                        "Fecha Solicitud: " + rs.getDate("Fecha de Solicitud") + " | " +
                        "Estado: " + rs.getString("Estado")
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(consultarSolicitud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
