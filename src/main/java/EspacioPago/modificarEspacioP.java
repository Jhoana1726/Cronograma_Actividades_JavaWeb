package EspacioPago;

import conexion.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class modificarEspacioP {

    public static void main(String[] args) {
        conexion con = new conexion();
        Connection cn;
        PreparedStatement ps;
        Statement st;
        ResultSet rs;

        //ID del espacio pago que se quiere modificar
        int id_editar = 11;  

        //Nuevos datos
        int id_espacio = 13;
        double costo = 200000;
        Date fecha_pago = Date.valueOf("2025-10-29");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = con.getConnection();
            if (cn == null) {
                System.out.println("No se pudo conectar a la base de datos.");
                return;
            }

          
            String sql = "UPDATE Espacio_Pago SET id_espacio = ?, costo = ?, fecha_pago = ? WHERE id_pago = ?";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, id_espacio);
            ps.setDouble(2, costo);
            ps.setDate(3, fecha_pago);
            ps.setInt(4, id_editar);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Registro con id_espacio " + id_editar + " actualizado correctamente.");
            } else {
                System.out.println(" No se encontró el registro con id_espacio " + id_editar);
            }

            // Mostrar todos los espacios después de la actualización
            st = cn.createStatement();
            rs = st.executeQuery("SELECT * FROM Espacio_Pago");

            System.out.println("Registros actuales en la tabla Espacio Pago:");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id_espacio") + ": "
                        + rs.getDouble("costo") + " - "
                        + rs.getDate("fecha_pago") + " - "
                );
            }

        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(modificarEspacioP.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
