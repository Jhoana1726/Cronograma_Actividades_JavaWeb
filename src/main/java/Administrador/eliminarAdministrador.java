package Administrador;

import conexion.conexion;
import java.sql.Connection;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class eliminarAdministrador {
    public static void main(String[] args) {
        conexion con = new conexion();
        Connection cn;
        Statement st;

        int id_admin = 17; // ID del administrador a eliminar

        try {
            cn = con.getConnection();
            st = cn.createStatement();

            String sql = "DELETE FROM Administrador WHERE id_admin=" + id_admin;
            int filas = st.executeUpdate(sql);

            if (filas > 0) {
                System.out.println("Administrador ID " + id_admin + " eliminado correctamente.");
            } else {
                System.out.println("No se encontró el Administrador con ID " + id_admin);
            }

        } catch (Exception e) {
            Logger.getLogger(eliminarAdministrador.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
