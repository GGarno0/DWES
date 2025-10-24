<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Consultar Sueldo</title>
</head>
<body>
    <h2>Consultar Sueldo de un Empleado</h2>
    <form action="EmpleadoController" method="post">
        <input type="hidden" name="action" value="verSueldo">
        <label>DNI del empleado:</label><br><br>
        <input type="text" name="dni" required maxlength="10"><br><br>
        <input type="submit" value="Ver Sueldo">
    </form>

    <div class="volver">
        <a href="<%= request.getContextPath() %>/index.jsp">← Volver</a>
    </div>
</body>
</html>
