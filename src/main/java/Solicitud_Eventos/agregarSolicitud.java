package Solicitud_Eventos;

import conexion.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class agregarSolicitud {

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

            //Obtener automáticamente el presidente activo
            String sqlPresidente = "SELECT id_presidente FROM Presidente_Junta WHERE activo = 1 LIMIT 1";
            ps = cn.prepareStatement(sqlPresidente);
            rs = ps.executeQuery();
            int id_presidente;
            if (rs.next()) {
                id_presidente = rs.getInt("id_presidente");
                System.out.println("Presidente activo detectado con id: " + id_presidente);
            } else {
                System.out.println("No hay presidente activo registrado.");
                return;
            }

            //Preguntar si se quiere usar un evento existente
            System.out.println("¿Desea usar un evento existente? (si/no)");
            String usarExistente = sc.nextLine().trim().toLowerCase();

            int id_evento;
            if (usarExistente.equals("si")) {
                System.out.println("Ingrese el ID del evento existente:");
                id_evento = Integer.parseInt(sc.nextLine());

                // Verificar que el evento exista y esté en Borrador
                String sqlCheck = "SELECT id_evento, fecha_inicio FROM Evento WHERE id_evento = ? AND estado = 'Borrador'";
                ps = cn.prepareStatement(sqlCheck);
                ps.setInt(1, id_evento);
                rs = ps.executeQuery();
                if (!rs.next()) {
                    System.out.println("El evento no existe o no está en estado Borrador.");
                    return;
                }

                // Validar que la fecha de inicio no sea anterior a la actual
                Timestamp fechaInicioEvento = rs.getTimestamp("fecha_inicio");
                Timestamp fechaActual = Timestamp.valueOf(LocalDateTime.now());
                if (fechaInicioEvento.before(fechaActual)) {
                    throw new IllegalArgumentException("No se puede usar un evento cuya fecha de inicio ya pasó.");
                }

                System.out.println("Usando evento existente con id: " + id_evento);

            } else {
                // Crear un nuevo evento
                System.out.println("Creando un nuevo evento...");

                System.out.println("Título:");
                String titulo_evento = sc.nextLine();

                System.out.println("Descripción:");
                String descripcion_evento = sc.nextLine();

                System.out.println("Fecha inicio (AAAA-MM-DD HH:MM:SS):");
                Timestamp fecha_inicio = Timestamp.valueOf(sc.nextLine());

                System.out.println("Fecha fin (AAAA-MM-DD HH:MM:SS):");
                Timestamp fecha_fin = Timestamp.valueOf(sc.nextLine());

                // Validación: fechas no pueden ser anteriores a hoy
                Timestamp fechaActual = Timestamp.valueOf(LocalDateTime.now());
                if (fecha_inicio.before(fechaActual) || fecha_fin.before(fechaActual)) {
                    throw new IllegalArgumentException("No se pueden registrar eventos con fechas anteriores a la actual.");
                }

                // Validación: fecha_fin no puede ser antes de fecha_inicio
                if (fecha_fin.before(fecha_inicio)) {
                    throw new IllegalArgumentException(" La fecha de fin no puede ser anterior a la fecha de inicio.");
                }

                System.out.println("ID organizador:");
                int id_organizador = Integer.parseInt(sc.nextLine());

                System.out.println("ID espacio:");
                int id_espacio = Integer.parseInt(sc.nextLine());

                String sqlInsertEvento = "INSERT INTO Evento (titulo, descripcion, fecha_inicio, fecha_fin, estado, id_organizador, id_espacio) VALUES (?, ?, ?, ?, ?, ?, ?)";
                ps = cn.prepareStatement(sqlInsertEvento, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setString(1, titulo_evento);
                ps.setString(2, descripcion_evento);
                ps.setTimestamp(3, fecha_inicio);
                ps.setTimestamp(4, fecha_fin);
                ps.setString(5, "Borrador");
                ps.setInt(6, id_organizador);
                ps.setInt(7, id_espacio);
                ps.executeUpdate();

                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    id_evento = rs.getInt(1);
                    System.out.println("Evento creado con id: " + id_evento);
                } else {
                    throw new SQLException("No se pudo obtener el ID del nuevo evento.");
                }
            }

            //Insertar la solicitud vinculada al evento
            Date fecha_solicitud = Date.valueOf(java.time.LocalDate.now());
            String estado_solicitud = "Pendiente";

            String sqlInsertSolicitud = "INSERT INTO Solicitud_Eventos (id_evento, id_presidente, fecha_solicitud, estado) VALUES (?, ?, ?, ?)";
            ps = cn.prepareStatement(sqlInsertSolicitud);
            ps.setInt(1, id_evento);
            ps.setInt(2, id_presidente);
            ps.setDate(3, fecha_solicitud);
            ps.setString(4, estado_solicitud);
            ps.executeUpdate();

            System.out.println("Solicitud insertada correctamente para el evento con id: " + id_evento);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(agregarSolicitud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
