package Espacio;

import conexion.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class modificarEspacio {

    public static void main(String[] args) {
        conexion con = new conexion();
        Connection cn;
        PreparedStatement ps;
        Statement st;
        ResultSet rs;

        //ID del espacio que se quiere modificar
        int id_editar = 11;  

        //Nuevos datos
        String nombre = "Auditorio Renovado";
        String ubicacion = "Calle 123 #45-67";
        int capacidad = 500;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = con.getConnection();
            if (cn == null) {
                System.out.println("No se pudo conectar a la base de datos.");
                return;
            }

          
            String sql = "UPDATE Espacio SET nombre = ?, ubicacion = ?, capacidad = ? WHERE id_espacio = ?";
            ps = cn.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, ubicacion);
            ps.setInt(3, capacidad);
            ps.setInt(4, id_editar);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Registro con id_espacio " + id_editar + " actualizado correctamente.");
            } else {
                System.out.println(" No se encontró el registro con id_espacio " + id_editar);
            }

            // Mostrar todos los espacios después de la actualización
            st = cn.createStatement();
            rs = st.executeQuery("SELECT * FROM Espacio");

            System.out.println("Registros actuales en la tabla Usuario:");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id_espacio") + ": "
                        + rs.getString("nombre") + " - "
                        + rs.getString("ubicacion") + " - "
                        + rs.getInt("capacidad")
                );
            }

        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(modificarEspacio.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
