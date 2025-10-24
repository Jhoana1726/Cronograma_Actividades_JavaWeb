package Certificado_Eventos;

import conexion.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class eliminarCertificadoEv {

    public static void main(String[] args) {
        conexion con = new conexion();
        Connection cn;
        PreparedStatement ps;
        Statement st;
        ResultSet rs;

        int id_eliminar = 11;

        try {
            cn = con.getConnection();
            if (cn == null) {
                System.out.println("No se pudo conectar a la base de datos.");
                return;
            }

            // Eliminar certificado
            String sql = "DELETE FROM Certificado_Eventos WHERE id_certificado = ?";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, id_eliminar);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Certificado con id " + id_eliminar + " eliminado correctamente.");
            } else {
                System.out.println("No se encontró el certificado con id " + id_eliminar);
            }

            // Mostrar certificados restantes
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
            Logger.getLogger(eliminarCertificadoEv.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
