<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<title>Buscar Empleado</title>

</head>
<body>
	<h2>Buscar Empleado</h2>
	<form action="EmpleadoController" method="post">
		<input type="hidden" name="action" value="buscar"> <label>Introduce
			nombre, DNI o categoría:</label><br>
		<br> <input type="text" name="criterio" required><br>
		<br> <input type="submit" value="Buscar">
	</form>

	<div class="volver">
		<a href="<%= request.getContextPath() %>/index.jsp">← Volver</a>
	</div>
</body>
</html>
