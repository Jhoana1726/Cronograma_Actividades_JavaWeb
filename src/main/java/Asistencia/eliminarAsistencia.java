package Asistencia;

import conexion.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class eliminarAsistencia {

    public static void main(String[] args) {
        conexion con = new conexion();
        Connection cn;
        PreparedStatement ps;
        Statement st;
        ResultSet rs;

        int id_eliminar = 12;

        try {
            cn = con.getConnection();
            if (cn == null) {
                System.out.println("No se pudo conectar a la base de datos.");
                return;
            }

            // Eliminar registro
            String sql = "DELETE FROM Asistencia WHERE id_asistencia = ?";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, id_eliminar);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Registro con id " + id_eliminar + " eliminado correctamente.");
            } else {
                System.out.println("No se encontró el registro con id " + id_eliminar);
            }

            // Mostrar registros restantes
            st = cn.createStatement();
            rs = st.executeQuery(
                "SELECT a.id_asistencia, e.titulo AS nombre_evento, u.nombre AS nombre_usuario, a.confirmo, a.asistio " +
                "FROM Asistencia a " +
                "JOIN Evento e ON a.id_evento = e.id_evento " +
                "JOIN Usuario u ON a.id_usuario = u.id_usuario"
            );

            System.out.println("Registros actuales en la tabla Asistencia:");
            while (rs.next()) {
                System.out.println(
                    rs.getInt("id_asistencia") + ": "
                    + rs.getString("nombre_evento") + " - "
                    + rs.getString("nombre_usuario") + " - "
                    + (rs.getBoolean("confirmo") ? "Sí" : "No") + " - "
                    + (rs.getBoolean("asistio") ? "Sí" : "No")
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(eliminarAsistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
