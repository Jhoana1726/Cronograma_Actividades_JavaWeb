package Espacio;

import conexion.conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class consultarEspacio {
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
            rs = st.executeQuery("SELECT * FROM Espacio"); // where id_espacio = 14

            // Recorrer los resultados
            while (rs.next()) {
                System.out.println(
                    rs.getInt("id_espacio") + ": "  + "Lugar: "
                    + rs.getString("nombre") + " - "  + "Dirección: "
                    + rs.getString("ubicacion") + " - "  + "Capacidad: "
                    + rs.getInt("capacidad") 
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(consultarEspacio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
