package Presidente_Junta;

import conexion.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;

public class modificarPresidente {

    public static void main(String[] args) {
        conexion con = new conexion();
        Connection cn;
        PreparedStatement ps;
        Statement st;
        ResultSet rs;
        Scanner sc = new Scanner(System.in);

        try {
            cn = con.getConnection();
            if (cn == null) {
                System.out.println("No se pudo conectar a la base de datos.");
                return;
            }

            st = cn.createStatement();

            System.out.println("Ingrese el ID del presidente a modificar:");
            int id_presidente = Integer.parseInt(sc.nextLine());

            System.out.println("Nuevo periodo:");
            String nuevo_periodo = sc.nextLine();

            System.out.println("Desea activar este presidente? (si/no)");
            boolean activar = sc.nextLine().trim().equalsIgnoreCase("si");

            // Si se activa, desactivar los demás
            if (activar) {
                String sqlDesactivar = "UPDATE Presidente_Junta SET activo = FALSE";
                st.executeUpdate(sqlDesactivar);
            }

            String sqlModificar = "UPDATE Presidente_Junta SET periodo = ?, activo = ? WHERE id_presidente = ?";
            ps = cn.prepareStatement(sqlModificar);
            ps.setString(1, nuevo_periodo);
            ps.setBoolean(2, activar);
            ps.setInt(3, id_presidente);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Presidente modificado correctamente.");
            } else {
                System.out.println("No se encontró el presidente con ID " + id_presidente);
            }

            // Mostrar todos los presidentes
            rs = st.executeQuery("SELECT pj.id_presidente, u.nombre, pj.periodo, pj.activo FROM Presidente_Junta pj JOIN Usuario u ON pj.id_presidente = u.id_usuario");
            System.out.println("Presidentes:");
            while (rs.next()) {
                System.out.println(
                    rs.getInt("id_presidente") + " : " 
                    + rs.getString("nombre") + " : "
                    + rs.getString("periodo") + " : "
                    + (rs.getBoolean("activo") ? "Activo" : "Inactivo"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(modificarPresidente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
