package Evento;

import conexion.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class modificarEvento {
    public enum Estado {
        Borrador,Revisión,Aprobado,Publicado
    }

    public static void main(String[] args) {
        conexion con = new conexion();
        Connection cn;
        PreparedStatement ps;
        Statement st;
        ResultSet rs;

        //ID del espacio pago que se quiere modificar
        int id_editar = 11;  

        //Nuevos datos
        String titulo = "Conferencia IA";
        String descripcion = "Evento sobre inteligencia artificial. Encuentro 3";
        LocalDateTime  fecha_inicio = LocalDateTime.of(2025,9,1,9,0,0);
        LocalDateTime  fecha_fin =  LocalDateTime.of(2025,9,1, 18,0,0);
        Estado estado = Estado.Borrador;
        int id_organizador = 22;
        int id_espacio = 11;
        Integer evento_padre = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = con.getConnection();
            if (cn == null) {
                System.out.println("No se pudo conectar a la base de datos.");
                return;
            }

          
            String sql = "UPDATE Evento SET titulo = ?, descripcion = ?, "
                    + "fecha_inicio = ?, fecha_fin = ?, estado = ?,"
                    + " id_organizador = ?, id_espacio = ?, evento_padre = ? WHERE id_evento = ?";
            ps = cn.prepareStatement(sql);
            ps.setString(1, titulo);
            ps.setString(2, descripcion);
            ps.setTimestamp(3, Timestamp.valueOf(fecha_inicio));
            ps.setTimestamp(4, Timestamp.valueOf(fecha_fin));
            ps.setString(5, estado.name());
            ps.setInt(6, id_organizador);
            ps.setInt(7, id_espacio);
            
            if (evento_padre == null) {
                ps.setNull(8, java.sql.Types.INTEGER);
            } else {
                ps.setInt(8, evento_padre);
            }
            
            ps.setInt(9, id_editar);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Registro con id_evento " + id_editar + " actualizado correctamente.");
            } else {
                System.out.println(" No se encontró el registro con id_evento " + id_editar);
            }

            // Mostrar todos los espacios después de la actualización
            st = cn.createStatement();
            rs = st.executeQuery("SELECT * FROM Evento");

            System.out.println("Registros actuales en la tabla Evento:");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id_evento") + ": "
                        + rs.getString("titulo") + " | "
                        + rs.getString("estado") + " | "
                        + rs.getTimestamp("fecha_inicio") + " -> "
                        + rs.getTimestamp("fecha_fin")
                        + rs.getString("estado") + " - " 
                        + rs.getInt("id_organizador") + " - " 
                        + rs.getInt("id_espacio") + " - " 
                        + rs.getInt("evento_padre")
                );
            }

        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(modificarEvento.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
