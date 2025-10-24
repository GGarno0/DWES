package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Empleado;
import dao.EmpleadoDAO;

@WebServlet("/EmpleadoController")
public class EmpleadoController extends HttpServlet {

	private EmpleadoDAO empleadoDAO;

	@Override
	public void init() {
		empleadoDAO = new EmpleadoDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action == null)
			action = "menu";

		try {
			switch (action) {
			case "listar":
				listarEmpleados(request, response);
				break;
			case "sueldoForm":
				mostrarFormularioSueldo(request, response);
				break;
			case "buscarForm":
				mostrarFormularioBusqueda(request, response);
				break;
			default:
				mostrarMenu(request, response);
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "Error al procesar la solicitud: " + e.getMessage());
			request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		try {
			switch (action) {
			case "verSueldo":
				mostrarSueldo(request, response);
				break;
			case "buscar":
				buscarEmpleado(request, response);
				break;
			default:
				mostrarMenu(request, response);
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "Error en operación POST: " + e.getMessage());
			request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
		}
	}

	// Metodos auxiliares

	private void mostrarMenu(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/menu.jsp").forward(request, response);
	}

	private void listarEmpleados(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		List<Empleado> lista = empleadoDAO.listarTodos();
		request.setAttribute("empleados", lista);
		request.getRequestDispatcher("/WEB-INF/views/listar.jsp").forward(request, response);
	}

	private void mostrarFormularioSueldo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/formSueldo.jsp").forward(request, response);
	}

	private void mostrarSueldo(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String dni = request.getParameter("dni");
		Double sueldo = empleadoDAO.obtenerSueldoPorDni(dni);
		request.setAttribute("dni", dni);
		request.setAttribute("sueldo", sueldo);
		request.getRequestDispatcher("/WEB-INF/views/verSueldo.jsp").forward(request, response);
	}

	private void mostrarFormularioBusqueda(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/formBuscar.jsp").forward(request, response);
	}

	private void buscarEmpleado(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String criterio = request.getParameter("criterio");
		List<Empleado> resultados = empleadoDAO.buscar(criterio);
		request.setAttribute("resultados", resultados);
		request.getRequestDispatcher("/WEB-INF/views/resultadosBusqueda.jsp").forward(request, response);
	}
}