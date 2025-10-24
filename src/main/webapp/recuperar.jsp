<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Recuperar Contraseña</title>
    <link rel="stylesheet" href="css/styles.css" />
</head>

<body class="auth-body">
    <div class="auth-container">
        <h2>Recuperar Contraseña</h2>

        <form action="recuperar" method="POST">
            <input type="email" name="email" placeholder="Ingresa tu correo registrado" required />
            <button type="submit">Enviar enlace</button>
        </form>

        <div class="auth-links">
            <a href="login.jsp">¿Ya tienes cuenta? Inicia sesión</a><br />
            <a href="registro.jsp">¿No estás registrado? Regístrate aquí</a><br />
            <a href="index.jsp">Volver al inicio</a>
        </div>
    </div>
</body>

</html>
