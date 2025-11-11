<%@ page contentType="text/html; charset=ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modificar Datos del Empleado</title>
<link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <h2>Modificar Datos de Empleado</h2>

    <form action="empresa" method="get" style="text-align:center;">
        <input type="hidden" name="accion" value="modificar">
        <input type="hidden" name="op" value="guardar">

        <label>DNI:</label>
        <input type="text" name="dni" required><br><br>

        <label>Nombre:</label>
        <input type="text" name="nombre" required><br><br>

        <label>Sexo:</label>
        <select name="sexo">
            <option value="M">M</option>
            <option value="F">F</option>
        </select><br><br>

        <label>Categoría:</label>
        <input type="number" name="categoria" min="1" max="5" required><br><br>

        <label>Años trabajados:</label>
        <input type="number" name="anyos" min="0" required><br><br>

        <button type="submit">Guardar cambios</button>
    </form>

    <div style="text-align:center; margin-top:20px;">
        <a href="../Menu/pagina.jsp">Volver al menú</a>
    </div>
</body>
</html>
