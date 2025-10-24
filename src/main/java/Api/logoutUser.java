package Api;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "logoutUser", urlPatterns = {"/logoutUser"})
public class logoutUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener la sesión actual
        HttpSession session = request.getSession(false);

        // Si hay sesión activa, eliminarla
        if (session != null) {
            session.invalidate(); // Elimina los datos de sesión
        }
        // Redirigir a la pagina principal
        response.sendRedirect("index.html");
    }
    @Override
    public String getServletInfo() {
        return "Servlet para cerrar sesión del usuario";
    }
}
