<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Consultar Salario</title>
<link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <h2>Consultar Salario de un Empleado</h2>

    <form action="empresa" method="get" style="text-align:center;">
        <input type="hidden" name="accion" value="sueldo">
        <label>DNI del empleado:</label>
        <input type="text" name="dni" required>
        <button type="submit">Consultar</button>
    </form>

    <%
        Double sueldo = (Double) request.getAttribute("sueldo");
        String dni = (String) request.getAttribute("dni");

        // Solo mostramos el h3 si realmente se ha consultado y hay un valor válido
        if (sueldo != null && dni != null && !dni.isEmpty()) {
    %>
        <h3>El sueldo del empleado con DNI <%= dni %> es: <%= sueldo %> &euro;</h3>
    <% } %>

    <div>
        <a href="../Menu/pagina.jsp">Volver al menú</a>
    </div>
</body>
</html>
