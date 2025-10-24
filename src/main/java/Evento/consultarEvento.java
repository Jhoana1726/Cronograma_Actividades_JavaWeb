package Evento;

import conexion.conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class consultarEvento {
    public static void main(String[] args) {
        conexion con = new conexion();
        Connection cn;
        Statement st;
        ResultSet rs;

        try {
            cn = con.getConnection();
            if (cn == null) {
                System.out.println("No se pudo establecer conexión con la base de datos");
                return;
            }

            st = cn.createStatement();
            rs = st.executeQuery("SELECT * FROM Evento"); 

            // Recorrer los resultados
            while (rs.next()) {
                System.out.println(
                      rs.getInt("id_evento") + " : " 
                    + rs.getString("titulo") + " - " 
                    + rs.getString("descripcion") + " - " 
                    + rs.getTimestamp("fecha_inicio") + " - " 
                    + rs.getTimestamp("fecha_fin") + " - "
                    + rs.getString("estado") + " - " 
                    + rs.getInt("id_organizador") + " - " 
                    + rs.getInt("id_espacio") + " - " 
                    + rs.getInt("evento_padre"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(consultarEvento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
