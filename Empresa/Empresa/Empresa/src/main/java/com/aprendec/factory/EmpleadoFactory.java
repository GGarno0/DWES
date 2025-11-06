package com.aprendec.factory;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.aprendec.model.Empleado;

/**
 * Patrón Factory Method
 * Crea objetos Empleado a partir de un ResultSet.
 */
public class EmpleadoFactory {

    public static Empleado crearDesdeResultSet(ResultSet rs) throws SQLException {
        Empleado e = new Empleado();
        e.setDni(rs.getString("dni"));
        e.setNombre(rs.getString("nombre"));
        e.setSexo(rs.getString("sexo").charAt(0));
        e.setCategoria(rs.getInt("categoria"));
        e.setAniosTrabajados(rs.getInt("anyos"));
        return e;
    }

    public static Empleado crearVacio() {
        Empleado e = new Empleado();
        e.setDni("");
        e.setNombre("Sin nombre");
        e.setSexo(' ');
        e.setCategoria(0);
        e.setAniosTrabajados(0);
        return e;
    }
}
