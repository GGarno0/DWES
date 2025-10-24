<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<title>Resultado del Sueldo</title>
</head>
<body>
	<div class="card">
		<%
            String dni = (String) request.getAttribute("dni");
            Double sueldo = (Double) request.getAttribute("sueldo");
            if (sueldo != null) {
        %>
		<h3>
			El sueldo del empleado con DNI <b><%= dni %></b> es:
		</h3>
		<h2><%= sueldo %>
			€
		</h2>
		<% } else { %>
		<h3>
			No se encontró ninguna nómina para el DNI
			<%= dni %>.
		</h3>
		<% } %>
	</div>

	<div class="volver">
		<a href="<%= request.getContextPath() %>/index.jsp">← Volver</a>
	</div>
</body>
</html>
