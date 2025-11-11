package com.aprendec.mediator;

import java.util.List;
import com.aprendec.model.Empleado;

/**
 * Interfaz Mediator
 * Define la comunicación central entre capas.
 */
public interface Mediator {
    List<Empleado> listarEmpleados() throws Exception;
    double buscarSueldoPorDni(String dni) throws Exception;
    void actualizarEmpleado(Empleado e) throws Exception;
}
