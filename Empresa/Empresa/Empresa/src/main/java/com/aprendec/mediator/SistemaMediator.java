package com.aprendec.mediator;

import java.util.List;
import com.aprendec.dao.EmpleadoDAO;
import com.aprendec.model.Empleado;

/**
 * Implementación concreta del Mediator.
 * Coordina la comunicación entre el controlador y el DAO.
 */
public class SistemaMediator implements Mediator {

    private EmpleadoDAO empleadoDAO;

    public SistemaMediator() {
        this.empleadoDAO = new EmpleadoDAO();
    }

    @Override
    public List<Empleado> listarEmpleados() throws Exception {
        return empleadoDAO.listarEmpleados();
    }

    @Override
    public double buscarSueldoPorDni(String dni) throws Exception {
        return empleadoDAO.buscarSalarioPorDni(dni);
    }

    @Override
    public void actualizarEmpleado(Empleado e) throws Exception {
        empleadoDAO.actualizarEmpleado(e);
    }
}
