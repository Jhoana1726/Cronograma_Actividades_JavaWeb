package Administrador;

import conexion.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class modificarAdministrador {
    public static void main(String[] args) {
        conexion con = new conexion();
        Connection cn;
        PreparedStatement ps;

        int id_admin = 3; 
        String nombre = "Alex Gironza";
        String email = "alexandergironza@gmail.com";
        String celular = "3123456789";
        String genero = "M";
        int edad = 40;
        String cedula = "123456789";
        String barrio = "La Arboleda";
        int estrato = 3;

        try {
            cn = con.getConnection();
            String sql = "UPDATE Usuario SET nombre=?, email=?, celular=?, genero=?, edad=?, cedula=?, barrio=?, estrato=? WHERE id_usuario=?";
            ps = cn.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, email);
            ps.setString(3, celular);
            ps.setString(4, genero);
            ps.setInt(5, edad);
            ps.setString(6, cedula);
            ps.setString(7, barrio);
            ps.setInt(8, estrato);
            ps.setInt(9, id_admin);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Administrador ID " + id_admin + " actualizado correctamente.");
            } else {
                System.out.println("No se encontró el Administrador con ID " + id_admin);
            }

        } catch (Exception e) {
            Logger.getLogger(modificarAdministrador.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
