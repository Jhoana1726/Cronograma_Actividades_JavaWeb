package Organizador;

import conexion.conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class agregarOrganizador {
    public enum Genero { F, M, Otro }

    public static void main(String[] args) {
        conexion con = new conexion();
        Connection cn;
        Statement st;
        ResultSet rs;

        // Datos del usuario nuevo
        String nombre = "Carlos Pérez";
        String email = "carlos@gmail.com";
        String celular = "3009876543";
        Genero genero = Genero.M;
        int edad = 30;
        String cedula = "98765432";
        String barrio = "El Prado";
        int estrato = 3;

        Integer idUsuarioExistente = 15; // si ya existe colocar el ID aquí

        try {
            cn = con.getConnection();
            st = cn.createStatement();

            // Usuario nuevo
            if (idUsuarioExistente == null) {
                String sqlUsuario = "INSERT INTO Usuario (nombre, email, celular, genero, edad, cedula, barrio, estrato) VALUES ('"
                        + nombre + "','" + email + "','" + celular + "','" + genero + "','" + edad + "','" + cedula + "','" + barrio + "','" + estrato + "')";
                st.executeUpdate(sqlUsuario, Statement.RETURN_GENERATED_KEYS);
                rs = st.getGeneratedKeys();
                if (rs.next()) {
                    idUsuarioExistente = rs.getInt(1);
                }
                System.out.println("Usuario creado con ID: " + idUsuarioExistente);
            }

            // Insertar en Organizador
            String sqlOrganizador = "INSERT INTO Organizador (id_organizador) VALUES (" + idUsuarioExistente + ")";
            st.executeUpdate(sqlOrganizador);
            System.out.println("Usuario " + idUsuarioExistente + " asignado como organizador.");

        } catch (Exception e) {
            Logger.getLogger(agregarOrganizador.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
