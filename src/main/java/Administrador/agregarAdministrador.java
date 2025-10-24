package Administrador;

import conexion.conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class agregarAdministrador {
    public static void main(String[] args) {
        conexion con = new conexion();
        Connection cn;
        Statement st;
        ResultSet rs;

        // ID de un usuario existente o null si se quiere crear uno nuevo
        Integer idUsuarioExistente = 3; 

        try {
            cn = con.getConnection();
            st = cn.createStatement();

            if (idUsuarioExistente != null) {
                // Asignar el rol de administrador
                String sql = "INSERT INTO Administrador (id_admin) VALUES (" + idUsuarioExistente + ")";
                st.executeUpdate(sql);
                System.out.println("Usuario ID " + idUsuarioExistente + " asignado como Administrador.");
            } else {
                System.out.println("Primero debes crear un usuario para asignarle el rol de Administrador.");
            }

            // Consultar todos los administradores
            rs = st.executeQuery("SELECT a.id_admin, u.nombre, u.email FROM Administrador a JOIN Usuario u ON a.id_admin = u.id_usuario");
            System.out.println("Administradores:");
            while (rs.next()) {
                System.out.println("ID Admin: " + rs.getInt("id_admin") + " | Nombre: " + rs.getString("nombre") + " | Correo: " + rs.getString("email"));
            }

        } catch (Exception e) {
            Logger.getLogger(agregarAdministrador.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
