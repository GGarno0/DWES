package com.aprendec.dao;

import java.sql.*;
import java.util.*;
import com.aprendec.model.Empleado;
import com.aprendec.conexion.Conexion;
import com.aprendec.factory.EmpleadoFactory;

/**
 * DAO (Data Access Object)
 * Encargado del acceso a la base de datos.
 * Es usado por el Mediator.
 */
public class EmpleadoDAO {

    public List<Empleado> listarEmpleados() throws Exception {
        List<Empleado> lista = new ArrayList<>();
        Connection con = Conexion.getConnection();
        String sql = "SELECT e.* FROM empleados e LEFT JOIN nominas n ON e.dni = n.empleado_dni";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Empleado e = EmpleadoFactory.crearDesdeResultSet(rs);
            lista.add(e);
        }

        con.close();
        return lista;
    }

    public double buscarSalarioPorDni(String dni) throws Exception {
        Connection con = Conexion.getConnection();
        String sql = """
                SELECT e.nombre, e.dni, n.sueldo
                FROM empleados e
                JOIN nominas n ON e.dni = n.empleado_dni
                WHERE e.dni = ?
                """;
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, dni);
        ResultSet rs = ps.executeQuery();
        double sueldo = 0;
        if (rs.next()) sueldo = rs.getDouble("sueldo");
        con.close();
        return sueldo;
    }

    public Empleado obtenerEmpleado(String dni) throws Exception {
        Connection con = Conexion.getConnection();
        String sql = """
                SELECT e.*, n.sueldo
                FROM empleados e
                LEFT JOIN nominas n ON e.dni = n.empleado_dni
                WHERE e.dni = ?
                """;
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, dni);
        ResultSet rs = ps.executeQuery();
        Empleado e = null;
        if (rs.next()) {
            e = EmpleadoFactory.crearDesdeResultSet(rs);
        }
        con.close();
        return e;
    }

    public void actualizarEmpleado(Empleado e) throws Exception {
        Connection con = Conexion.getConnection();
        String sql = "UPDATE empleados SET nombre=?, sexo=?, categoria=?, anyos=? WHERE dni=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, e.getNombre());
        ps.setString(2, String.valueOf(e.getSexo()));
        ps.setInt(3, e.getCategoria());
        ps.setInt(4, e.getAniosTrabajados());
        ps.setString(5, e.getDni());
        ps.executeUpdate();

        double nuevoSalario = calcularSueldo(e.getCategoria(), e.getAniosTrabajados());
        PreparedStatement ps2 = con.prepareStatement("UPDATE nominas SET sueldo=? WHERE empleado_dni=?");
        ps2.setDouble(1, nuevoSalario);
        ps2.setString(2, e.getDni());
        ps2.executeUpdate();

        con.close();
    }

    private double calcularSueldo(int categoria, int anyos) {
        double base;
        switch (categoria) {
            case 1 -> base = 1000;
            case 2 -> base = 1400;
            case 3 -> base = 1800;
            case 4 -> base = 2300;
            default -> base = 900;
        }
        return Math.round(base + anyos * 25);
    }
}
