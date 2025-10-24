package Usuario;

import conexion.conexion;
import java.sql.Connection; 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class agregarUsuario {
    
    public enum Genero {
        F, M, Otro
    }
    
    public static void main(String[] args) {
        conexion con = new conexion();
        Connection cn;
        Statement st;
        ResultSet rs;

        String nombre = "Alexander Gironza";
        String email = "alex29284@gmail.com";
        String password = "12345";
        String celular = "3105678934";
        Genero genero = Genero.M;
        int edad = 24;
        String cedula = "1005678904";
        String barrio = "La Paz";
        int estrato = 2;
        
        try {
            cn = con.getConnection();
            st = cn.createStatement();

            //Verificar si el correo ya existe
            String checkEmail = "SELECT * FROM Usuario WHERE email = '" + email + "'";
            rs = st.executeQuery(checkEmail);
            
            if (rs.next()) {
                // Si encuentra un resultado, el correo ya está registrado
                System.out.println("Error: El correo '" + email + "' ya está registrado en el sistema.");
            } else {
                //Si el correo no existe, insertar nuevo usuario
                String sql = "INSERT INTO Usuario (nombre, email, password, celular, genero, edad, cedula, barrio, estrato) VALUES ('" 
                        + nombre + "','" + email + "','" + password + "','" + celular + "','" + genero + "','" + edad + "','" 
                        + cedula + "','" + barrio + "','" + estrato + "')";
                
                st.executeUpdate(sql);
                System.out.println("Registro insertado correctamente.");

                //Consultar todos los registros
                rs = st.executeQuery("SELECT * FROM Usuario");
                while (rs.next()) {
                    System.out.println(
                        rs.getInt("id_usuario") + " : " 
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
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(agregarUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
