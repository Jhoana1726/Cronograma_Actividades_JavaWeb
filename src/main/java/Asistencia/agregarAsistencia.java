package Asistencia;

import conexion.conexion;
import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class agregarAsistencia {

    public static void main(String[] args) {
        conexion con = new conexion();

        int id_evento = 11;   // ID del evento al que se confirma asistencia
        int id_usuario = 20;  // ID del usuario que confirma asistencia
        boolean confirmo = true;
        boolean asistio = false;

        // Consulta para obtener fechas del evento
        String sqlFecha = "SELECT fecha_inicio, fecha_fin, titulo FROM Evento WHERE id_evento = ?";

        // insertar asistencia
        String sqlInsert = "INSERT INTO Asistencia (id_evento, id_usuario, confirmo, asistio) VALUES (?, ?, ?, ?)";
        
        // mostrar asistencias con datos del evento y usuario
        String sqlSelect = 
            "SELECT a.id_asistencia, e.titulo AS nombre_evento, u.nombre AS nombre_usuario, e.fecha_inicio, e.fecha_fin, a.confirmo, a.asistio " +
            "FROM Asistencia a " +
            "JOIN Evento e ON a.id_evento = e.id_evento " +
            "JOIN Usuario u ON a.id_usuario = u.id_usuario";

        try (Connection cn = con.getConnection();
             PreparedStatement psFecha = cn.prepareStatement(sqlFecha);
             PreparedStatement psInsert = cn.prepareStatement(sqlInsert);
             PreparedStatement psSelect = cn.prepareStatement(sqlSelect)) {

            //Obtener las fechas del evento
            psFecha.setInt(1, id_evento);
            ResultSet rsFecha = psFecha.executeQuery();

            if (!rsFecha.next()) {
                System.out.println("Error: No se encontró el evento con ID " + id_evento);
                return;
            }

            String tituloEvento = rsFecha.getString("titulo");
            Timestamp fechaInicio = rsFecha.getTimestamp("fecha_inicio");
            Timestamp fechaFin = rsFecha.getTimestamp("fecha_fin");
            LocalDateTime ahora = LocalDateTime.now();

            //Validar que el evento no haya comenzado ni finalizado
            if (fechaFin != null && fechaFin.toLocalDateTime().isBefore(ahora)) {
                System.out.println("Error: El evento '" + tituloEvento + "' ya finalizó (" + fechaFin + "). No se puede registrar asistencia.");
                return;
            }
            if (fechaInicio != null && fechaInicio.toLocalDateTime().isBefore(ahora)) {
                System.out.println("Error: El evento '" + tituloEvento + "' ya comenzó (" + fechaInicio + "). No se puede registrar asistencia.");
                return;
            }

            //Insertar asistencia solo si el evento es futuro
            psInsert.setInt(1, id_evento);
            psInsert.setInt(2, id_usuario);
            psInsert.setBoolean(3, confirmo);
            psInsert.setBoolean(4, asistio);
            psInsert.executeUpdate();

            System.out.println("Asistencia registrada correctamente para el evento '" + tituloEvento + "'.");

            //Mostrar todas las asistencias registradas
            try (ResultSet rs = psSelect.executeQuery()) {
                System.out.println("\nAsistencias registradas:");
                while (rs.next()) {
                    System.out.println(
                        rs.getInt("id_asistencia") + " : " +
                        rs.getString("nombre_evento") + " (" + rs.getTimestamp("fecha_inicio") + " - " +
                        rs.getTimestamp("fecha_fin") + ") - " +
                        rs.getString("nombre_usuario") + " - Confirmó: " +
                        rs.getBoolean("confirmo") + " - Asistió: " +
                        rs.getBoolean("asistio")
                    );
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(agregarAsistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
