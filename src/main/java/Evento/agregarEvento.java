package Evento;

import conexion.conexion;
import java.sql.Connection; 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;  
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class agregarEvento {
     public enum Estado {
        Borrador, Revisión, Aprobado, Publicado
    }
   
    public static void main(String[] args) {
        conexion con = new conexion();
        Connection cn;
        Statement st;
        ResultSet rs;

        String titulo = "Workshop de Ciberseguridad";
        String descripcion = "Seguridad informática y hacking ético";
        LocalDateTime fecha_inicio = LocalDateTime.of(2025, 9, 4, 14, 0);
        LocalDateTime fecha_fin = LocalDateTime.of(2025, 9, 4, 18, 0);
        Estado estado = Estado.Borrador;
        int id_organizador = 22;
        int id_espacio = 11;
        Integer evento_padre = null; // null si es evento independiente

        try {
            cn = con.getConnection();
            st = cn.createStatement();

            // Convertir LocalDateTime a Timestamp
            Timestamp ts_inicio = Timestamp.valueOf(fecha_inicio);
            Timestamp ts_fin = Timestamp.valueOf(fecha_fin);

            // Construir SQL
            String sql;
            if (evento_padre == null) {
                sql = "INSERT INTO Evento (titulo, descripcion, fecha_inicio, fecha_fin, estado, id_organizador, id_espacio, evento_padre) VALUES ("
                        + "'" + titulo + "',"
                        + "'" + descripcion + "',"
                        + "'" + ts_inicio + "',"
                        + "'" + ts_fin + "',"
                        + "'" + estado + "',"
                        + id_organizador + ","
                        + id_espacio + ","
                        + "NULL)"; // NULL para evento independiente
            } else {
                sql = "INSERT INTO Evento (titulo, descripcion, fecha_inicio, fecha_fin, estado, id_organizador, id_espacio, evento_padre) VALUES ("
                        + "'" + titulo + "',"
                        + "'" + descripcion + "',"
                        + "'" + ts_inicio + "',"
                        + "'" + ts_fin + "',"
                        + "'" + estado + "',"
                        + id_organizador + ","
                        + id_espacio + ","
                        + evento_padre + ")"; 
            }

            // Ejecutar INSERT
            st.executeUpdate(sql);
            System.out.println("Registro de evento insertado correctamente");

            // Consultar todos los registros de Evento
            rs = st.executeQuery("SELECT * FROM Evento");
            while (rs.next()) {
                System.out.println(rs.getInt("id_evento") + " : " 
                    + rs.getString("titulo") + " - " 
                    + rs.getString("descripcion") + " - " 
                    + rs.getTimestamp("fecha_inicio") + " - " 
                    + rs.getTimestamp("fecha_fin") + " - "
                    + rs.getString("estado") + " - " 
                    + rs.getInt("id_organizador") + " - " 
                    + rs.getInt("id_espacio") + " - " 
                    + rs.getString("evento_padre")); // devuelve NULL si no tiene padre
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(agregarEvento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
