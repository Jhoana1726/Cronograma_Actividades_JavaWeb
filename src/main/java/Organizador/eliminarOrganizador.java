package Organizador;

import conexion.conexion;
import java.sql.Connection;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class eliminarOrganizador {
    public static void main(String[] args) {
        conexion con = new conexion();
        Connection cn;
        Statement st;

        int idOrganizador = 15; // ID del organizador a eliminar

        try {
            cn = con.getConnection();
            st = cn.createStatement();

            String sql = "DELETE FROM Organizador WHERE id_organizador = " + idOrganizador;
            int filas = st.executeUpdate(sql);

            if (filas > 0) {
                System.out.println("Organizador con ID " + idOrganizador + " eliminado correctamente.");
            } else {
                System.out.println("No se encontró organizador con ID " + idOrganizador);
            }

        } catch (Exception e) {
            Logger.getLogger(eliminarOrganizador.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
