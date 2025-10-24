
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Menú de Opciones</title>
<link rel="stylesheet" type="text/css" href="./css/style.css">
</head>
<body>
	<h1>Menu de Opciones</h1>
	<table border=1>
		<tr>
			<td><a href="EmpleadoController?action=listar">Opción
					1:Mostrar Todos los empleados</a></td>
		</tr>
		<tr>
			<td><a href="EmpleadoController?action=sueldoForm">Opción
					2:Mostrar salario de un empleado</a></td>
		</tr>
		<tr>
			<td><a href="EmpleadoController?action=buscarForm">Opción
					3:Modificar datos de un empleado</a></td>
		</tr>
	</table>
</body>
</html>
