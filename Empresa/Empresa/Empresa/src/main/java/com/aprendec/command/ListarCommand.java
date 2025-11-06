package com.aprendec.command;

import com.aprendec.mediator.Mediator;
import com.aprendec.mediator.SistemaMediator;
import com.aprendec.model.Empleado;
import java.util.List;
import javax.servlet.http.*;

public class ListarCommand implements Command {
    @Override
    public void ejecutar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Mediator mediator = new SistemaMediator();
        List<Empleado> lista = mediator.listarEmpleados();
        request.setAttribute("empleados", lista);
        request.getRequestDispatcher("views/MostrarEmpleados.jsp").forward(request, response);
    }
}
