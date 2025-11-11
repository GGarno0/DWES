<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ page import="java.util.*, com.aprendec.model.Empleado" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lista de Empleados</title>
<link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <h2>Listado de Empleados</h2>

    <table border="1" cellpadding="8" cellspacing="0" align="center">
        <tr>
            <th>DNI</th>
            <th>Nombre</th>
            <th>Sexo</th>
            <th>Categoría</th>
            <th>Años Trabajados</th>
        </tr>

        <%
            List<Empleado> empleados = (List<Empleado>) request.getAttribute("empleados");
            if (empleados != null && !empleados.isEmpty()) {
                for (Empleado e : empleados) {
        %>
        <tr>
            <td><%= e.getDni() %></td>
            <td><%= e.getNombre() %></td>
            <td><%= e.getSexo() %></td>
            <td><%= e.getCategoria() %></td>
            <td><%= e.getAniosTrabajados() %></td>
        </tr>
        <%      }
            } else {
        %>
        <tr><td colspan="5" align="center">No hay empleados registrados.</td></tr>
        <% } %>
    </table>

    <div>
        <a href="../Menu/pagina.jsp">Volver al menú</a>
    </div>
</body>
</html>
