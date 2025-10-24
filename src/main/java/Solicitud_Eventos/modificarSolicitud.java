package Solicitud_Eventos;

import conexion.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class modificarSolicitud {

    public static void main(String[] args) {
        conexion con = new conexion();
        Connection cn;
        PreparedStatement ps;
        Statement st;
        ResultSet rs;
        Scanner sc = new Scanner(System.in);

        try {
            cn = con.getConnection();
            if (cn == null) {
                System.out.println("No se pudo conectar a la base de datos.");
                return;
            }

            st = cn.createStatement();

            System.out.println("Ingrese el ID de la solicitud a modificar:");
            int id_solicitud = Integer.parseInt(sc.nextLine());

            System.out.println("Nuevo estado (Pendiente, Aceptada, Rechazada):");
            String nuevo_estado = sc.nextLine().trim();

            // Antes de modificar, verificamos la fecha del evento asociado
            String sqlCheckFecha = "SELECT e.fecha_inicio, e.titulo FROM Solicitud_Eventos se " +
                                   "JOIN Evento e ON se.id_evento = e.id_evento " +
                                   "WHERE se.id_solicitud = ?";
            ps = cn.prepareStatement(sqlCheckFecha);
            ps.setInt(1, id_solicitud);
            rs = ps.executeQuery();

            if (!rs.next()) {
                System.out.println("No se encontró ninguna solicitud con ID " + id_solicitud);
                return;
            }

            Timestamp fechaEvento = rs.getTimestamp("fecha_inicio");
            String tituloEvento = rs.getString("titulo");
            Timestamp fechaActual = Timestamp.valueOf(LocalDateTime.now());

            // Si la fecha del evento ya pasó, no se puede aprobar
            if (nuevo_estado.equalsIgnoreCase("Aceptada") && fechaEvento.before(fechaActual)) {
                System.out.println("No se puede aprobar la solicitud. El evento '" + tituloEvento + "' ya tiene una fecha pasada.");
                return;
            }

            //  Si pasa las validaciones, se actualiza el estado
            String sqlUpdate = "UPDATE Solicitud_Eventos SET estado = ? WHERE id_solicitud = ?";
            ps = cn.prepareStatement(sqlUpdate);
            ps.setString(1, nuevo_estado);
            ps.setInt(2, id_solicitud);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Solicitud modificada correctamente. Nuevo estado: " + nuevo_estado);
            } else {
                System.out.println("No se encontró la solicitud con ID " + id_solicitud);
            }

            // Mostrar todas las solicitudes actualizadas
            rs = st.executeQuery(
                "SELECT se.id_solicitud, e.titulo AS nombre_evento, u.nombre AS presidente, se.fecha_solicitud, se.estado " +
                "FROM Solicitud_Eventos se " +
                "JOIN Evento e ON se.id_evento = e.id_evento " +
                "JOIN Presidente_Junta pj ON se.id_presidente = pj.id_presidente " +
                "JOIN Usuario u ON pj.id_presidente = u.id_usuario"
            );

            System.out.println("Solicitudes actuales:");
            while (rs.next()) {
                System.out.println(
                    rs.getInt("id_solicitud") + " : "
                    + rs.getString("nombre_evento") + " : "
                    + rs.getString("presidente") + " : "
                    + rs.getDate("fecha_solicitud") + " : "
                    + rs.getString("estado")
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(modificarSolicitud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
