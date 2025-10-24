package Usuario;

import conexion.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class modificarUsuario {

    // Enum para controlar valores válidos en género
    public enum Genero {
        M("M"),
        F("F");

        private final String valor;

        Genero(String valor) {
            this.valor = valor;
        }

        public String getValor() {
            return valor;
        }
    }

    public static void main(String[] args) {
        conexion con = new conexion();
        Connection cn;
        PreparedStatement ps;
        Statement st;
        ResultSet rs;

        // ID del usuario que se quiere modificar
        int id_editar = 2;

        // Nuevos datos
        String nombre = "Laura Suarez";
        String email = "lau23san@gmail.com";
        String celular = "3105432678";
        Genero genero = Genero.F;
        int edad = 38;
        String cedula = "1002456783";
        String barrio = "El Uvo";
        int estrato = 2;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = con.getConnection();
            if (cn == null) {
                System.out.println("No se pudo conectar a la base de datos.");
                return;
            }

            //Verificar si el correo nuevo ya pertenece a otro usuario
            String verificarCorreo = "SELECT id_usuario FROM Usuario WHERE email = ? AND id_usuario <> ?";
            ps = cn.prepareStatement(verificarCorreo);
            ps.setString(1, email);
            ps.setInt(2, id_editar);
            rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Error: El correo '" + email + "' ya está siendo usado por otro usuario.");
                return; // Detiene la ejecución del UPDATE
            }

            //Actualizar usuario
            String sql = "UPDATE Usuario SET nombre = ?, email = ?, celular = ?, genero = ?, edad = ?, cedula = ?, barrio = ?, estrato = ? WHERE id_usuario = ?";
            ps = cn.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, email);
            ps.setString(3, celular);
            ps.setString(4, genero.getValor());
            ps.setInt(5, edad);
            ps.setString(6, cedula);
            ps.setString(7, barrio);
            ps.setInt(8, estrato);
            ps.setInt(9, id_editar);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Registro con id_usuario " + id_editar + " actualizado correctamente.");
            } else {
                System.out.println("No se encontró el registro con id_usuario " + id_editar);
            }

            //Mostrar todos los usuarios después de la actualización
            st = cn.createStatement();
            rs = st.executeQuery("SELECT * FROM Usuario");

            System.out.println("Registros actuales en la tabla Usuario:");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id_usuario") + ": "
                        + rs.getString("nombre") + " - "
                        + rs.getString("email") + " - "
                        + rs.getString("celular") + " - "
                        + rs.getString("genero") + " - "
                        + rs.getInt("edad") + " - "
                        + rs.getString("cedula") + " - "
                        + rs.getString("barrio") + " - "
                        + rs.getInt("estrato")
                );
            }

        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(modificarUsuario.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
