package com.aprendec.factory;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.aprendec.model.Nomina;

/**
 * Patrón Factory Method
 * Crea objetos Nomina a partir de un ResultSet.
 */
public class NominaFactory {

    public static Nomina crearDesdeResultSet(ResultSet rs) throws SQLException {
        Nomina n = new Nomina();
        n.setEmpleadoDni(rs.getString("empleado_dni"));
        n.setSueldo(rs.getDouble("sueldo"));
        return n;
    }
}
