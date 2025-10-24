package EspacioPago;

import conexion.conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class consultarEspacioP {
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
            rs = st.executeQuery("SELECT * FROM Espacio_Pago"); // where id_espacio_pago = 14

            // Recorrer los resultados
            while (rs.next()) {
                System.out.println(
                    rs.getInt("id_pago") + ": " + "Id_Espacio: "
                    + rs.getInt("id_espacio") + " - " + "Costo: "
                    + rs.getInt("costo") + " - " + "Fecha de pago: "
                    + rs.getDate("fecha_pago") 
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(consultarEspacioP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
