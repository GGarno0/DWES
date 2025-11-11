package com.aprendec.command;

import com.aprendec.mediator.Mediator;
import com.aprendec.mediator.SistemaMediator;
import com.aprendec.model.Empleado;
import javax.servlet.http.*;

public class ModificarCommand implements Command {
    @Override
    public void ejecutar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Mediator mediator = new SistemaMediator();

        if ("guardar".equals(request.getParameter("op"))) {
            Empleado e = new Empleado();
            e.setDni(request.getParameter("dni"));
            e.setNombre(request.getParameter("nombre"));
            e.setSexo(request.getParameter("sexo").charAt(0));
            e.setCategoria(Integer.parseInt(request.getParameter("categoria")));
            e.setAniosTrabajados(Integer.parseInt(request.getParameter("anyos")));
            mediator.actualizarEmpleado(e);
        }

        request.getRequestDispatcher("views/ModificarDatos.jsp").forward(request, response);
    }
}
