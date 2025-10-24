package EspacioPago;

import conexion.conexion;
import java.sql.Connection; 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;  
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class agregarEspacioP {
   
    public static void main(String[] args) {
        conexion con = new conexion();
        Connection cn;
        Statement st;
        ResultSet rs;

        int id_espacio = 13;
        double costo = 185000.50;
        Date fecha_pago = Date.valueOf("2025-10-29");
        
        // Insertar datos
            String sql = "INSERT INTO Espacio_Pago (id_espacio, costo, fecha_pago ) VALUES ('" + id_espacio + "','" + costo + "','" + fecha_pago + "')";
        
        try {
            cn = con.getConnection();
            st = cn.createStatement();
            st.executeUpdate(sql); // Ejecutar INSERT
            System.out.println("Registro de espacio pagado insertado correctamente");

            // Consultar todos los registros
            rs = st.executeQuery("SELECT * FROM Espacio_Pago");
            while (rs.next()) {
                System.out.println(rs.getInt("id_pago") + " : " 
                    + rs.getInt("id_espacio") + " - " 
                    + rs.getInt("costo") + " - " 
                    + rs.getDate("fecha_pago"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(agregarEspacioP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
