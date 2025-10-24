package Presidente_Junta;

import conexion.conexion;
import java.sql.Connection; 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class agregarPresidente {

    public enum Genero {
        F, M, Otro
    }

    public static void main(String[] args) {
        conexion con = new conexion();
        Connection cn;
        Statement st;
        ResultSet rs;

        // Datos del nuevo usuario (si no existe)
        String nombre = "Anne Valentina Narvaez";
        String email = "annevalen2@gmail.com";
        String password = "12345";
        String celular = "3107415896";
        Genero genero = Genero.F;
        int edad = 24;
        String cedula = "1002962084";
        String barrio = "Centro";
        int estrato = 4;

        // Periodo del presidente
        String periodo = "2028-2029";

        // Si el usuario ya existe, aquí su id
        Integer idUsuarioExistente = null;

        try {
            cn = con.getConnection();
            st = cn.createStatement();

            // Caso 1: Usuario nuevo
            if (idUsuarioExistente == null) {
                String sqlUsuario = "INSERT INTO Usuario (nombre, email, password, celular, genero, edad, cedula, barrio, estrato) VALUES ('"
                        + nombre + "','" + email + "','" + password + "','" + celular + "','" + genero + "','" + edad + "','" + cedula + "','" + barrio + "','" + estrato + "')";
                
                st.executeUpdate(sqlUsuario, Statement.RETURN_GENERATED_KEYS);
                rs = st.getGeneratedKeys();
                if (rs.next()) {
                    idUsuarioExistente = rs.getInt(1);
                }
                System.out.println("Usuario creado con ID: " + idUsuarioExistente);
            }

            //  Desactivar presidentes anteriores
            String sqlDesactivar = "UPDATE Presidente_Junta SET activo = FALSE";
            st.executeUpdate(sqlDesactivar);

            // Insertar nuevo presidente
            String sqlPresidente = "INSERT INTO Presidente_Junta (id_presidente, periodo, activo) VALUES ("
                    + idUsuarioExistente + ",'" + periodo + "', TRUE)";
            st.executeUpdate(sqlPresidente);
            System.out.println("Usuario " + idUsuarioExistente + " asignado como presidente en el periodo " + periodo + " (activo)");

            // Mostrar tabla Usuario
            rs = st.executeQuery("SELECT * FROM Usuario");
            System.out.println("Usuarios:");
            while (rs.next()) {
                System.out.println(rs.getInt("id_usuario") + " : " 
                    + rs.getString("nombre") + " - " 
                    + rs.getString("email") + " - " 
                    + rs.getString("celular") + " - "  
                    + rs.getString("genero") + " - " 
                    + rs.getInt("edad") + " - " 
                    + rs.getString("cedula") + " - " 
                    + rs.getString("barrio") + " - " 
                    + rs.getInt("estrato"));
            }

            // Mostrar tabla Presidente_Junta
            rs = st.executeQuery(
                "SELECT pj.id_presidente, u.nombre, pj.periodo, pj.activo " +
                "FROM Presidente_Junta pj " +
                "JOIN Usuario u ON pj.id_presidente = u.id_usuario");
            System.out.println("Presidentes:");
            while (rs.next()) {
                System.out.println(
                    rs.getInt("id_presidente") + " : " 
                    + rs.getString("nombre") + " : "
                    + rs.getString("periodo") + " : "
                    + (rs.getBoolean("activo") ? "Activo" : "Inactivo"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(agregarPresidente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
