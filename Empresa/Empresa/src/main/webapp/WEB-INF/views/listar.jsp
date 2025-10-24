<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.*,model.Empleado"%>
<!DOCTYPE html>
<html>
<head>
<title>Lista de Empleados</title>
</head>
<body>
	<h2>Listado de Empleados</h2>

	<table>
		<tr>
			<th>DNI</th>
			<th>Nombre</th>
			<th>Sexo</th>
			<th>Categoría</th>
			<th>Años trabajados</th>
			<th>Sueldo (€)</th>
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
			<td><%= e.getAnyos() %></td>
			<td><%= e.getSueldo() %></td>
		</tr>
		<%  } } else { %>
		<tr>
			<td colspan="6">No hay empleados registrados</td>
		</tr>
		<% } %>
	</table>

	<div class="volver">
		<a href="<%= request.getContextPath() %>/index.jsp">← Volver</a>
	</div>
</body>
</html>
