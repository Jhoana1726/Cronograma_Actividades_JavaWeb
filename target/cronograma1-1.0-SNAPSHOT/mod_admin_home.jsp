<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Panel Administrador</title>
    <link rel="stylesheet" href="css/administrador.css">
</head>

<body>
    <header>
        <h1>Panel del Administrador</h1>
        <nav>
            <ul>
                <li><a href="mod_admin_gestion_usuarios.jsp" target="_blank">Usuarios</a></li>
                <li><a href="muroAdmin.jsp" target="_blank">Perfil</a></li>
                <li><a href="mod_admin_gestion_eventos.jsp" target="_blank">Eventos</a></li>
                <li><a href="#" target="_blank">Estadísticas</a></li>
                <li><a href="#" target="_blank">Encuestas</a></li>
                <li><a href="#" target="_blank">Comentarios</a></li>
                <li><a href="#" target="_blank">Historial</a></li>
                <li><a href="#" target="_blank">Exportar</a></li>
                <li><a href="logoutUser" style="color: red; font-weight: bold;">Cerrar sesión</a> </li>
            </ul>
        </nav>
    </header>

    <main>
        <section class="bienvenida">
            <h2>Bienvenido, Administrador</h2>
            <p>Desde aquí puedes gestionar todo el sistema de eventos comunitarios.</p>
        </section>

        <section class="accesos">
            <div class="card">
                <h3>Gestión de Usuarios</h3>
                <p>Crear, editar y eliminar usuarios.</p>
                <a href="mod_admin_gestion_usuarios.jsp" target="_blank">Ir</a>
            </div>
            <div class="card">
                <h3>Gestión de Eventos</h3>
                <p>Crear eventos, editarlos o aprobarlos.</p>
                <a href="mod_admin_gestion_eventos.jsp" target="_blank">Ir</a>
            </div>
            <div class="card">
                <h3>Estadísticas</h3>
                <p>Ver participación y asistencia en eventos.</p>
                <a href="#" target="_blank">Ir</a>
            </div>  
            <div class="card">
                <h3>Publicaciones</h3>
                <p>Crear encuestas, noticias o avisos.</p>
                <a href="muroAdmin.jsp" target="_blank">Ir</a>
            </div>
        </section>
    </main>

    <footer>
        <p>© Plataforma Comunitaria 2025</p>
    </footer>
</body>

</html>
