
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error de Ingreso</title>
        <link rel="stylesheet" href="css/styles.css" />
    </head>
    <body class="auth-body">
        <div class="auth-container">
            <p class="error-message">
                <%= request.getAttribute("error") != null ? request.getAttribute("error") : "Credenciales inválidas" %>
            </p>
            <a href="login.jsp" class="btn">Volver al login</a>
        </div>
    </body>
</html>