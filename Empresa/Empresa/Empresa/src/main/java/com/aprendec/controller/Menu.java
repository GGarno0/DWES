package com.aprendec.controller;

import com.aprendec.command.Command;
import com.aprendec.command.CommandFactory;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Patrón Front Controller
 * Punto de entrada único que distribuye las peticiones a los comandos.
 */
@WebServlet("/empresa")
public class Menu extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        try {
            Command comando = CommandFactory.getCommand(accion);
            if (comando != null) {
                comando.ejecutar(request, response);
            } else {
                response.sendRedirect("pagina.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("exception", e);
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
