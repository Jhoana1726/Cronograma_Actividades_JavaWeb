package Certificado_Eventos;

import conexion.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class modificarCertificadoEv {

    public static void main(String[] args) {
        conexion con = new conexion();
        Connection cn;
        PreparedStatement ps;
        Statement st;
        ResultSet rs;

        int id_editar = 11;  // ID del certificado a modificar
        int id_evento = 12;
        int id_usuario = 18;
        Date fecha_entrega = Date.valueOf("2025-09-01");

        try {
            cn = con.getConnection();
            if (cn == null) {
                System.out.println("No se pudo conectar a la base de datos.");
                return;
            }

            String sql = "UPDATE Certificado_Eventos SET id_evento = ?, id_usuario = ?, fecha_entrega = ? WHERE id_certificado = ?";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, id_evento);
            ps.setInt(2, id_usuario);
            ps.setDate(3, fecha_entrega);
            ps.setInt(4, id_editar);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Certificado con id_certificado " + id_editar + " actualizado correctamente.");
            } else {
                System.out.println("No se encontró el certificado con id_certificado " + id_editar);
            }

            // Mostrar todos los certificados después de la modificación
            st = cn.createStatement();
            rs = st.executeQuery(
                "SELECT c.id_certificado, e.titulo AS nombre_evento, u.nombre AS nombre_usuario, c.fecha_entrega " +
                "FROM Certificado_Eventos c " +
                "JOIN Evento e ON c.id_evento = e.id_evento " +
                "JOIN Usuario u ON c.id_usuario = u.id_usuario"
            );

            System.out.println("Registros actuales en la tabla Certificado_Eventos:");
            while (rs.next()) {
                System.out.println(
                    rs.getInt("id_certificado") + ": "
                    + rs.getString("nombre_evento") + " - "
                    + rs.getString("nombre_usuario") + " - "
                    + rs.getDate("fecha_entrega")
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(modificarCertificadoEv.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
