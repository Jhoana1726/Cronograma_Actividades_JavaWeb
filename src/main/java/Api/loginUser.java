package Api;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import conexion.conexion;

@WebServlet(name = "loginUser", urlPatterns = {"/loginUser"})
public class loginUser extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //  Obtener datos enviados desde el formulario de login.jsp
        String email = request.getParameter("email");  
        String password = request.getParameter("password");

        // Verificar que los campos no estén vacíos
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            request.setAttribute("error", "Por favor completa todos los campos.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        // Conectarse a la base de datos
        conexion con = new conexion();
        Connection cn = con.getConnection();

        try {
            // Buscar el usuario en la base de datos por email y contraseña
            String sql = "SELECT * FROM Usuario WHERE email = ? AND password = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            // Si se encuentran coincidencia, crea la sesión y redirige al home de habitante
            if (rs.next()) {
                HttpSession session = request.getSession();
                session.setAttribute("nombre", rs.getString("nombre"));
                session.setAttribute("email", rs.getString("email"));
                request.getRequestDispatcher("mod_admin_home.jsp").forward(request, response);
            } else {
                //Si no encuentra, muestra error
                request.setAttribute("error", "Credenciales incorrectas. Verifica tu correo y contraseña.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

            ps.close();
            rs.close();
            cn.close();

        } catch (SQLException e) {
            // Capturar cualquier error de conexión o SQL
            request.setAttribute("error", "Error al conectar con la base de datos: " + e.getMessage());
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
