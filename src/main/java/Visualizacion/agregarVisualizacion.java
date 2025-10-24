package Visualizacion;

import conexion.conexion;
import java.sql.Connection; 
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class agregarVisualizacion {
   
    public static void main(String[] args) {
        conexion con = new conexion();
        Connection cn;
        Statement st;

        int id_evento = 12;   // ID de un evento existente
        int id_usuario = 17;  // ID de un usuario existente
        LocalDateTime fecha = LocalDateTime.now();

        // Insertar datos
        String sql = "INSERT INTO Visualizacion (id_evento, id_usuario, fecha) " +
                     "VALUES ('" + id_evento + "', '" + id_usuario + "', '" + fecha + "')";

        try {
            cn = con.getConnection();
            st = cn.createStatement();
            st.executeUpdate(sql); // Ejecutar INSERT
            System.out.println("Registro de visualización insertado correctamente");

        } catch (SQLException ex) {
            Logger.getLogger(agregarVisualizacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
