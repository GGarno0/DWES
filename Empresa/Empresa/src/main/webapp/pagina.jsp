<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Gestión de Empleados</title>
<link rel="stylesheet" href="css/styles.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;700&display=swap" rel="stylesheet">
<style>
.menu {
	text-align: center;
	list-style: none;
	padding: 0;
	margin: 40px auto;

	justify-content: center;
	gap: 20px;
}



.menu a {
	display: inline-block;
	text-decoration: none;
	color: ##6a0dad;
	background-color: #f1f5f9;
	border: 2px solid #8b5cf6;;
	padding: 12px 25px;
	border-radius: 10px;
	font-weight: 600;
	margin-bottom: 10px;
	transition: all 0.3s ease;
}

</style>
</head>

<body>
	<div class="container">
		<h1>Gestión de Empleados</h1>

		<ul class="menu">
			<li><a href="empresa?accion=listar">Mostrar Empleados</a></li>
			<li><a href="empresa?accion=sueldo">Mostrar Salario por DNI</a></li>
			<li><a href="empresa?accion=modificar">Modificar Datos</a></li>
		</ul>
	</div>
</body>
</html>
