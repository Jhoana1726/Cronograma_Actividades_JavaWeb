package Presidente_Junta;

import conexion.conexion;
import java.sql.Connection;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class eliminarPresidente {
    public static void main(String[] args) {
        conexion con = new conexion();
        Connection cn;
        Statement st;

        int idPresidente = 15; // ID del presidente a eliminar

        try {
            cn = con.getConnection();
            st = cn.createStatement();

            String sql = "DELETE FROM Presidente_Junta WHERE id_presidente = " + idPresidente;
            int filas = st.executeUpdate(sql);

            if (filas > 0) {
                System.out.println("Presidente con ID " + idPresidente + " eliminado correctamente.");
            } else {
                System.out.println("No se encontró presidente con ID " + idPresidente);
            }

        } catch (Exception e) {
            Logger.getLogger(eliminarPresidente.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
