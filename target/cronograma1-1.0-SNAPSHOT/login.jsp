<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Iniciar Sesión</title>
    <link rel="stylesheet" href="css/styles.css" />
</head>

<body class="auth-body">

    <div class="auth-container">
        <h2>Iniciar Sesión</h2>

        <% 
            
            // Sección para mostrar mensajes dinámicos enviados por el servlet
            // (por ejemplo, mensajes de error o confirmación de registro)
            
            String mensaje = (String) request.getAttribute("mensaje"); // Mensaje de éxito ( registro correcto)
            String error = (String) request.getAttribute("error");     // Mensaje de error ( credenciales incorrectas)

            // Si hay mensaje de éxito, lo muestra en verde
            if (mensaje != null) { 
        %>
            <p style="color: green; font-weight: bold;"><%= mensaje %></p>
        <% 
            } 
            // Si hay mensaje de error, lo muestra en rojo
            if (error != null) { 
        %>
            <p style="color: red; font-weight: bold;"><%= error %></p>
        <% 
            } 
        %>

        <!--  Formulario de inicio de sesión
             - El atributo "action" indica que los datos se envían al 
               servlet llamado "loginUser"
             - El método "POST" evita que los datos viajen por la URL -->
        <form action="loginUser" method="POST">
            <input type="text" name="email" placeholder="Correo electrónico" required />
            <input type="password" name="password" placeholder="Contraseña" required />
            <button type="submit">Ingresar</button>
        </form>

        <!--   Enlaces adicionales de navegación
             - Permiten acceder a otras páginas: registro, recuperación, inicio -->
        <div class="auth-links">
            <a href="registro.jsp">¿No estás registrado? ¡Regístrate aquí!</a><br />
            <a href="recuperar.jsp">¿Olvidaste tu contraseña?</a><br />
            <a href="index.jsp">Volver al inicio</a>
        </div>
    </div>

</body>

</html>
