package EspacioPago;

import conexion.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class eliminarEspacioP {

    public static void main(String[] args) {
        conexion con = new conexion();
        Connection cn;
        PreparedStatement ps;
        Statement st;
        ResultSet rs;

      
        int id_eliminar = 12;

        try {
           
            Class.forName("com.mysql.cj.jdbc.Driver");

            cn = con.getConnection(); 
            if (cn == null) {
                System.out.println("No se pudo conectar a la base de datos.");
                return;
            }

            //ELIMINAR registro
            String sql = "DELETE FROM Espacio_Pago WHERE id_pago = ?";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, id_eliminar);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Registro con id " + id_eliminar + " eliminado correctamente.");
            } else {
                System.out.println(" No se encontró el registro con id " + id_eliminar);
            }

            // MOSTRAR los registros restantes
            st = cn.createStatement();
            rs = st.executeQuery("SELECT * FROM Espacio_Pago");

            System.out.println("Registros actuales en la tabla:");
            while (rs.next()) {
                System.out.println(
                    rs.getInt("id_pago") + ": " + "Id_Espacio: "
                    + rs.getInt("id_espacio") + " - " + "Costo: "
                    + rs.getInt("costo") + " - " + "Fecha de pago: "
                    + rs.getDate("fecha_pago")
                );
            }

        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(eliminarEspacioP.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
