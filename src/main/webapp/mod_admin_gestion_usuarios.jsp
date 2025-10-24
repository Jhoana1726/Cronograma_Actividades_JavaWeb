<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Gestión de Usuarios</title>
  <link rel="stylesheet" href="css/administrador.css">
</head>
<body class="gestion-usuarios">
  <header>
    <h1>Gestión de Usuarios</h1>
    <a href="mod_admin_home.jsp" class="volver">Volver al Panel</a>
  </header>

  <section class="filtros">
    <input type="text" placeholder="Buscar por nombre o correo">
    <select>
      <option value="">Todos los roles</option>
      <option value="participante">Participante</option>
      <option value="organizador">Organizador</option>
      <option value="admin">Administrador</option>
      <option value="presidente">Presidente JAC</option>
    </select>
    <button>Buscar</button>
    <button onclick="window.location.href='mod_admin_nuevo_usuario.jsp'">+ Nuevo Evento</button>
  </section>

  <section class="tabla">
    <table>
      <thead>
        <tr>
          <th>Nombre</th>
          <th>Correo</th>
          <th>Rol</th>
          <th>Barrio</th>
          <th>Acciones</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>Anne Valentina Gironza</td>
          <td>annevalentina@gmail.com</td>
          <td>Organizadora</td>
          <td>Villa Norte</td>
          <td>
            <button class="editar">Editar</button>
            <button class="eliminar">Eliminar</button>
            <button class="rol">Cambiar Rol</button>
          </td>
        </tr>
        <tr>
          <td>Alexander Gironza</td>
          <td>alexgironza@gmail.com</td>
          <td>Participante</td>
          <td>El Prado</td>
          <td>
            <button class="editar">Editar</button>
            <button class="eliminar">Eliminar</button>
            <button class="rol">Cambiar Rol</button>
          </td>
        </tr>
      </tbody>
    </table>
  </section>
</body>
</html>
