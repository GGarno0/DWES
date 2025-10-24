<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.*,model.Empleado"%>
<!DOCTYPE html>
<html>
<head>
<title>Resultados de Búsqueda</title>
</head>
<body>
	<h2 style="text-align: center;">Resultados de la Búsqueda</h2>

	<table>
		<tr>
			<th>DNI</th>
			<th>Nombre</th>
			<th>Sexo</th>
			<th>Categoría</th>
			<th>Años</th>
			<th>Sueldo</th>
		</tr>
		<%
		List<Empleado> resultados = (List<Empleado>) request.getAttribute("resultados");
		if (resultados != null && !resultados.isEmpty()) {
			for (Empleado e : resultados) {
		%>
		<tr>
			<td><%=e.getDni()%></td>
			<td><%=e.getNombre()%></td>
			<td><%=e.getSexo()%></td>
			<td><%=e.getCategoria()%></td>
			<td><%=e.getAnyos()%></td>
			<td><%=e.getSueldo()%></td>
		</tr>
		<%
		}
		} else {
		%>
		<tr>
			<td colspan="6">No se encontraron coincidencias.</td>
		</tr>
		<%
		}
		%>
	</table>

	<div class="volver">
		<a href="<%=request.getContextPath()%>/index.jsp">← Volver</a>
	</div>
</body>
</html>
