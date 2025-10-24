package Api;

import conexion.conexion;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "register", urlPatterns = {"/register"})
public class register extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get
        String usuario = request.getParameter("usuario");
        response.getWriter().println("Hola " + usuario + " (GET recibido desde Servlet)");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // parámetros enviados desde los formularios
        String nombre = request.getParameter("usuario");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String confirmPassword = request.getParameter("confirm_password");
        String celular = request.getParameter("celular");
        String genero = request.getParameter("genero");
        String edadStr = request.getParameter("edad");
        String cedula = request.getParameter("cedula");
        String barrio = request.getParameter("barrio");
        String estratoStr = request.getParameter("estrato");

        int edad = 0;
        int estrato = 0;

        // Convertir valores numéricos de forma segura
        try {
            if (edadStr != null && !edadStr.isEmpty()) {
                edad = Integer.parseInt(edadStr);
            }
            if (estratoStr != null && !estratoStr.isEmpty()) {
                estrato = Integer.parseInt(estratoStr);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Edad o estrato no válidos");
            request.getRequestDispatcher("registro.jsp").forward(request, response);
            return;
        }

        // Validación de contraseña segura
        String regexPassword = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$";
        if (!password.matches(regexPassword)) {
            request.setAttribute("error", "La contraseña debe tener al menos 8 caracteres, una mayúscula, una letra y un número.");
            request.getRequestDispatcher("registro.jsp").forward(request, response);
            return;
        }

        if (email != null) {
            // Caso de registro
            if (confirmPassword != null && !password.equals(confirmPassword)) {
                // Contraseñas no coinciden
                request.setAttribute("error", "Las contraseñas no coinciden");
                request.getRequestDispatcher("registro.jsp").forward(request, response);
                return;
            }

            try {
                conexion con = new conexion();
                Connection cn = con.getConnection();

                // Verificar si el correo ya existe
                String sqlCheck = "SELECT email FROM Usuario WHERE email = ?";
                PreparedStatement psCheck = cn.prepareStatement(sqlCheck);
                psCheck.setString(1, email);
                ResultSet rs = psCheck.executeQuery();

                if (rs.next()) {
                    request.setAttribute("error", "El correo electrónico ya está registrado. Intente con otro.");
                    request.getRequestDispatcher("registro.jsp").forward(request, response);
                    rs.close();
                    psCheck.close();
                    cn.close();
                    return;
                }

                rs.close();
                psCheck.close();

                // Registro correcto: insertar en la base de datos
                String sqlInsert = "INSERT INTO Usuario (nombre, email, password, celular, genero, edad, cedula, barrio, estrato) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement psInsert = cn.prepareStatement(sqlInsert);
                psInsert.setString(1, nombre);
                psInsert.setString(2, email);
                psInsert.setString(3, password);
                psInsert.setString(4, celular);
                psInsert.setString(5, genero);
                psInsert.setInt(6, edad);
                psInsert.setString(7, cedula);
                psInsert.setString(8, barrio);
                psInsert.setInt(9, estrato);
                psInsert.executeUpdate();

                // Mensaje de confirmación
                request.setAttribute("mensaje", "Usuario " + nombre + " registrado correctamente con email " + email);
                request.getRequestDispatcher("login.jsp").forward(request, response);

                psInsert.close();
                cn.close();

            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("error", "Error al conectar con la base de datos: " + e.getMessage());
                request.getRequestDispatcher("registro.jsp").forward(request, response);
            }

        } else {
            // Caso de login
            if ("Jhoana".equals(nombre) && "Sonic123".equals(password)) {
                request.getRequestDispatcher("mod_admin_home.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Credenciales incorrectas");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        }
    }
}
