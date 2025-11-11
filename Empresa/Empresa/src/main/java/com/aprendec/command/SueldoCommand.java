package com.aprendec.command;

import com.aprendec.mediator.Mediator;
import com.aprendec.mediator.SistemaMediator;
import javax.servlet.http.*;

public class SueldoCommand implements Command {
    @Override
    public void ejecutar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Mediator mediator = new SistemaMediator();
        String dni = request.getParameter("dni");
        double sueldo = mediator.buscarSueldoPorDni(dni);
        request.setAttribute("dni", dni);
        request.setAttribute("sueldo", sueldo);
        request.getRequestDispatcher("views/MostrarSalario.jsp").forward(request, response);
    }
}
