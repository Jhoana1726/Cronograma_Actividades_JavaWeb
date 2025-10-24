package Visualizacion;

import conexion.conexion;
import java.sql.Connection; 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class consultarVisualizacion {
   
    public static void main(String[] args) {
        conexion con = new conexion();
        Connection cn;
        Statement st;
        ResultSet rs;

        try {
            cn = con.getConnection();
            st = cn.createStatement();

            // Consultar todos los registros
            rs = st.executeQuery("SELECT v.id_visualizacion, v.fecha, u.nombre AS usuario, e.titulo AS evento " +
                                 "FROM Visualizacion v " +
                                 "INNER JOIN Usuario u ON v.id_usuario = u.id_usuario " +
                                 "INNER JOIN Evento e ON v.id_evento = e.id_evento");

            while (rs.next()) {
                System.out.println(rs.getInt("id_visualizacion") + " : " 
                    + rs.getString("usuario") + " vio el evento '" 
                    + rs.getString("evento") + "' el " 
                    + rs.getTimestamp("fecha"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(consultarVisualizacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
