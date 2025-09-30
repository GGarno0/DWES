package Laboral;

import java.sql.*;

public class BDNominas {

    // Datos de conexión
    private static final String URL = "jdbc:mysql://localhost:3307/nominas?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASS = "123456";

    // Alta de un empleado
    public static void altaEmpleado(Empleado e) {
        double sueldo = Nomina.sueldo(e);

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {

            // Comprobar si existe
            String sqlCheck = "SELECT COUNT(*) FROM Empleados WHERE dni=?";
            try (PreparedStatement psCheck = conn.prepareStatement(sqlCheck)) {
                psCheck.setString(1, e.DNI);
                ResultSet rs = psCheck.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    System.out.println("Empleado con DNI " + e.DNI + " ya existe.");
                    return;
                }
            }

            // Insertar en Empleados
            String sqlEmp = "INSERT INTO Empleados (dni, nombre, sexo, categoria, anyos) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement psEmp = conn.prepareStatement(sqlEmp)) {
                psEmp.setString(1, e.DNI);
                psEmp.setString(2, e.nombre);
                psEmp.setString(3, String.valueOf(e.sexo));
                psEmp.setInt(4, e.getCategoria());
                psEmp.setInt(5, e.anyo);
                psEmp.executeUpdate();
            }

            // Insertar en Nominas
            String sqlNom = "INSERT INTO Nominas (dni, sueldo) VALUES (?, ?)";
            try (PreparedStatement psNom = conn.prepareStatement(sqlNom)) {
                psNom.setString(1, e.DNI);
                psNom.setDouble(2, sueldo);
                psNom.executeUpdate();
            }

            System.out.println("Empleado dado de alta: " + e.nombre);

        } catch (SQLException ex) {
            System.err.println("Error al dar de alta empleado: " + ex.getMessage());
        }
    }

    // Mostrar todos los empleados
    public static void mostrarEmpleados() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "SELECT * FROM Empleados";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                System.out.println("DNI: " + rs.getString("dni")
                        + ", Nombre: " + rs.getString("nombre")
                        + ", Sexo: " + rs.getString("sexo")
                        + ", Categoría: " + rs.getInt("categoria")
                        + ", Años: " + rs.getInt("anyos"));
            }
        } catch (SQLException ex) {
            System.err.println("Error al mostrar empleados: " + ex.getMessage());
        }
    }

    // Mostrar sueldo de un empleado
    public static void mostrarSueldoEmpleado(String dni) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "SELECT sueldo FROM Nominas WHERE dni = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Sueldo: " + rs.getDouble("sueldo"));
            } else {
                System.out.println("Empleado no encontrado.");
            }
        } catch (SQLException ex) {
            System.err.println("Error al mostrar sueldo: " + ex.getMessage());
        }
    }

    // Modificar empleado
    public static void modificarEmpleado(String dni, int campo, String nuevo) {
        String sql = "";
        switch (campo) {
            case 1:
                sql = "UPDATE Empleados SET nombre=? WHERE dni=?";
                break;
            case 2:
                sql = "UPDATE Empleados SET categoria=? WHERE dni=?";
                break;
            case 3:
                sql = "UPDATE Empleados SET anyos=? WHERE dni=?";
                break;
        }

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            PreparedStatement ps = conn.prepareStatement(sql);
            if (campo == 2 || campo == 3) {
                ps.setInt(1, Integer.parseInt(nuevo));
            } else {
                ps.setString(1, nuevo);
            }
            ps.setString(2, dni);
            ps.executeUpdate();

            // Recalcular sueldo si cambia categoría o años
            if (campo == 2 || campo == 3) {
                recalcularSueldoEmpleado(dni);
            }
            System.out.println("Empleado actualizado.");
        } catch (SQLException ex) {
            System.err.println("Error al modificar empleado: " + ex.getMessage());
        }
    }

    // Recalcular sueldo de un empleado
    public static void recalcularSueldoEmpleado(String dni) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            String sqlEmp = "SELECT * FROM Empleados WHERE dni=?";
            PreparedStatement ps = conn.prepareStatement(sqlEmp);
            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Empleado e = new Empleado(
                        rs.getString("nombre"),
                        rs.getInt("categoria"),
                        rs.getInt("anyos"),
                        rs.getString("dni"),
                        rs.getString("sexo").charAt(0)
                );
                double sueldo = Nomina.sueldo(e);

                String sqlNom = "UPDATE Nominas SET sueldo=? WHERE dni=?";
                PreparedStatement psNom = conn.prepareStatement(sqlNom);
                psNom.setDouble(1, sueldo);
                psNom.setString(2, dni);
                psNom.executeUpdate();
                System.out.println("Sueldo actualizado para " + dni);
            }
        } catch (Exception ex) {
            System.err.println("Error al recalcular sueldo: " + ex.getMessage());
        }
    }

    // Recalcular sueldos de todos los empleados
    public static void recalcularSueldosTodos() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "SELECT * FROM Empleados";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                String dni = rs.getString("dni");
                Empleado e = new Empleado(
                        rs.getString("nombre"),
                        rs.getInt("categoria"),
                        rs.getInt("anyos"),
                        dni,
                        rs.getString("sexo").charAt(0)
                );
                double sueldo = Nomina.sueldo(e);

                String sqlNom = "UPDATE Nominas SET sueldo=? WHERE dni=?";
                PreparedStatement psNom = conn.prepareStatement(sqlNom);
                psNom.setDouble(1, sueldo);
                psNom.setString(2, dni);
                psNom.executeUpdate();
            }
            System.out.println("Sueldos de todos los empleados recalculados.");
        } catch (Exception ex) {
            System.err.println("Error al recalcular sueldos: " + ex.getMessage());
        }
    }
}
