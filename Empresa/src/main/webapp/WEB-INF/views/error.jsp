<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Error</title>
</head>
<body>
	<div class="card">
		<h2>Ocurrió un error</h2>
		<p><%= request.getAttribute("error") %></p>
		<div class="volver">
			<a href="<%= request.getContextPath() %>/index.jsp">← Volver al
				inicio</a>
		</div>
	</div>
</body>
</html>
