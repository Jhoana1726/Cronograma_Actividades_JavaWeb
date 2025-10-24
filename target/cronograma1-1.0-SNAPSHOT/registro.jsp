<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registro de Usuario</title>
    <link rel="stylesheet" href="css/styles.css">
</head>

<body class="auth-body">
<div class="auth-container">
    <h2>Crear Cuenta</h2>

    <% 
        String error = (String) request.getAttribute("error");
        String mensaje = (String) request.getAttribute("mensaje");
        if (error != null) { 
    %>
        <p style="color: red; font-weight: bold;"><%= error %></p>
    <% } 
       if (mensaje != null) { 
    %>
        <p style="color: green; font-weight: bold;"><%= mensaje %></p>
    <% } %>

    <form action="register" method="POST">
        <input type="text" name="usuario" placeholder="Nombre completo" required>
        <input type="email" name="email" placeholder="Correo electrónico" required>
        <input type="password" name="password" placeholder="Contraseña" required>
        <input type="password" name="confirm_password" placeholder="Confirmar contraseña" required>
        <input type="text" name="celular" placeholder="Celular">
        <select name="genero" required>
            <option value="">Seleccione género</option>
            <option value="F">Femenino</option>
            <option value="M">Masculino</option>
            <option value="Otro">Otro</option>
        </select>
        <input type="number" name="edad" placeholder="Edad" min="1">
        <input type="text" name="cedula" placeholder="Cédula">
        <input type="text" name="barrio" placeholder="Barrio">
        <input type="number" name="estrato" placeholder="Estrato" min="1" max="6">
        <button type="submit">Registrarse</button>
    </form>

    <div class="auth-links">
        <a href="login.jsp">¿Ya tienes cuenta? Inicia sesión</a><br>
        <a href="index.jsp">Volver al inicio</a>
    </div>
</div>
</body>
</html>
