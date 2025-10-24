<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Gestión de Eventos</title>
    <link rel="stylesheet" href="css/administrador.css">
</head>

<body class="gestion-eventos">

    <header>
        <a href="mod_admin_home.jsp" class="volver"> Volver al Panel</a>
        <h1>Gestión de Eventos</h1>
    </header>

    <!-- Filtros -->
    <section class="filtros-eventos">
        <input type="text" placeholder="Buscar por nombre del evento...">
        <select>
            <option value="">Todos los estados</option>
            <option value="borrador">Borrador</option>
            <option value="revision">Revisión</option>
            <option value="publicado">Publicado</option>
        </select>

        <button>Buscar</button>
        <button onclick="window.location.href='mod_admin_nuevo_evento.html'">+ Nuevo Evento</button>
    </section>

    <!-- Tabla de eventos -->
    <section class="tabla-eventos">
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre del Evento</th>
                    <th>Tipo</th>
                    <th>Lugar</th>
                    <th>Estado</th>
                    <th>Encargado</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>001</td>
                    <td>Jornada de Vacunación</td>
                    <td>Salud Animal</td>
                    <td>Parque Municipal</td>
                    <td><span class="estado-revision">Revisión</span></td>
                    <td>Dr. Juan Pérez</td>
                    <td class="acciones-evento">
                        <button class="ver">Ver</button>
                        <button class="editar">Editar</button>
                        <button class="devolver">Devolver</button><br>
                        <button class="eliminar">Eliminar</button>
                    </td>
                </tr>
                <tr>
                    <td>002</td>
                    <td>Festival Gastronómico</td>
                    <td>Cultural</td>
                    <td>Plaza Central</td>
                    <td><span class="estado-publicado">Publicado</span></td>
                    <td>Sra. María Gómez</td>
                    <td class="acciones-evento">
                        <button class="ver">Ver</button>
                        <button class="editar">Editar</button>
                        <button class="eliminar">Eliminar</button>
                    </td>
                </tr>
                <tr>
                    <td>003</td>
                    <td>Día del Árbol</td>
                    <td>Medio Ambiente</td>
                    <td>Bosque Urbano</td>
                    <td><span class="estado-borrador">Borrador</span></td>
                    <td>Comité Verde</td>
                    <td class="acciones-evento">
                        <button class="editar">Editar</button>
                        <button class="eliminar">Eliminar</button>
                    </td>
                </tr>
            </tbody>
        </table>
    </section>

</body>
</html>
