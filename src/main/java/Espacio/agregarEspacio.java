package Espacio;

import conexion.conexion;
import java.sql.Connection; 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class agregarEspacio {
   
    public static void main(String[] args) {
        conexion con = new conexion();
        Connection cn;
        Statement st;
        ResultSet rs;

        String nombre = "Polideportivo";
        String ubicacion = "Carrera 12 #65-34 ";
        int capacidad = 300;
        
        // Insertar datos
        String sql = "INSERT INTO Espacio (nombre, ubicacion, capacidad ) VALUES ('" + nombre + "','" + ubicacion + "','" + capacidad + "')";
        
        try {
            cn = con.getConnection();
            st = cn.createStatement();
            st.executeUpdate(sql); // Ejecutar INSERT
            System.out.println("Registro de espacio insertado correctamente");

            // Consultar todos los registros
            rs = st.executeQuery("SELECT * FROM Espacio");
            while (rs.next()) {
                System.out.println(rs.getInt("id_espacio") + " : " 
                    + rs.getString("nombre") + " - " 
                    + rs.getString("ubicacion") + " - " 
                    + rs.getInt("capacidad"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(agregarEspacio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
