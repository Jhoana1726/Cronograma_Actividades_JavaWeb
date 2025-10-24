package Organizador;

import conexion.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class modificarOrganizador {

    public enum Genero {
        F("Femenino"), M("Masculino"), Otro("Otro");

        private final String valor;
        Genero(String valor) { this.valor = valor; }
        public String getValor() { return valor; }
    }

    public static void main(String[] args) {
        conexion con = new conexion();
        Connection cn;
        PreparedStatement ps;
        ResultSet rs;

        // ID del organizador (y del usuario asociado) que se quiere editar
        int idOrganizador = 22;

        // Nuevos datos del usuario
        String nombre = "Carlos G. Pérez";
        String email = "carlos.gp@gmail.com";
        String celular = "3001234567";
        Genero genero = Genero.M;
        int edad = 60;
        String cedula = "98765432";
        String barrio = "El Prado";
        int estrato = 3;

        try {
            cn = con.getConnection();

            // Verificar que el usuario es un organizador
            String sqlVerificar = "SELECT id_organizador FROM Organizador WHERE id_organizador = ?";
            ps = cn.prepareStatement(sqlVerificar);
            ps.setInt(1, idOrganizador);
            rs = ps.executeQuery();

            if (rs.next()) {
                // Si existe, actualizar los datos en Usuario
                String sqlActualizar = "UPDATE Usuario SET nombre = ?, email = ?, celular = ?, genero = ?, edad = ?, cedula = ?, barrio = ?, estrato = ? WHERE id_usuario = ?";
                ps = cn.prepareStatement(sqlActualizar);
                ps.setString(1, nombre);
                ps.setString(2, email);
                ps.setString(3, celular);
                ps.setString(4, genero.getValor());
                ps.setInt(5, edad);
                ps.setString(6, cedula);
                ps.setString(7, barrio);
                ps.setInt(8, estrato);
                ps.setInt(9, idOrganizador);

                int filas = ps.executeUpdate();
                if (filas > 0) {
                    System.out.println("Datos del organizador ID " + idOrganizador + " actualizados correctamente.");
                } else {
                    System.out.println("No se pudo actualizar el organizador con ID " + idOrganizador);
                }

            } else {
                System.out.println("No se encontró un organizador con ID " + idOrganizador);
            }

        } catch (Exception e) {
            Logger.getLogger(modificarOrganizador.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
