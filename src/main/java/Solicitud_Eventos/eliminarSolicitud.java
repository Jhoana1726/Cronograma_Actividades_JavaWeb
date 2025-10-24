package Solicitud_Eventos;

import conexion.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class eliminarSolicitud {

    public static void main(String[] args) {
        conexion con = new conexion();
        Connection cn;
        PreparedStatement ps;
        Statement st;
        ResultSet rs;

        int id_eliminar = 11; // ID de la solicitud que se quiere eliminar

        try {
            cn = con.getConnection();
            if (cn == null) {
                System.out.println("No se pudo conectar a la base de datos.");
                return;
            }

            // Eliminar solicitud
            String sql = "DELETE FROM Solicitud_Eventos WHERE id_solicitud = ?";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, id_eliminar);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Solicitud con ID " + id_eliminar + " eliminada correctamente.");
            } else {
                System.out.println("No se encontró la solicitud con ID " + id_eliminar);
            }

            // Mostrar todas las solicitudes restantes
            st = cn.createStatement();
            rs = st.executeQuery("SELECT se.id_solicitud, e.titulo AS nombre_evento, u.nombre AS presidente, se.fecha_solicitud, se.estado " +
                                 "FROM Solicitud_Eventos se " +
                                 "JOIN Evento e ON se.id_evento = e.id_evento " +
                                 "JOIN Presidente_Junta pj ON se.id_presidente = pj.id_presidente " +
                                 "JOIN Usuario u ON pj.id_presidente = u.id_usuario");
            System.out.println("Solicitudes actuales:");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id_solicitud") + " : "
                        + rs.getString("nombre_evento") + " : "
                        + rs.getString("presidente") + " : "
                        + rs.getDate("fecha_solicitud") + " : "
                        + rs.getString("estado"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(eliminarSolicitud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
