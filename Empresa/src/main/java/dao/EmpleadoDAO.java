package dao;

import model.Empleado;
import conexion.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {

	public List<Empleado> listarTodos() throws SQLException {
		List<Empleado> lista = new ArrayList<>();
		String sql = "SELECT e.dni, e.nombre, e.sexo, e.categoria, e.anyos, n.sueldo "
				+ "FROM empleados e LEFT JOIN nominas n ON e.dni = n.dni " + "ORDER BY e.nombre";

		try (Connection c = Conexion.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				Empleado e = new Empleado(rs.getString("dni"), rs.getString("nombre"), rs.getString("sexo"),
						rs.getInt("categoria"), rs.getInt("anyos"),
						rs.getObject("sueldo") == null ? 0.0 : rs.getDouble("sueldo"));
				lista.add(e);
			}
		}
		return lista;
	}

	public Double obtenerSueldoPorDni(String dni) throws SQLException {
		String sql = "SELECT sueldo FROM nominas WHERE dni = ?";
		try (Connection c = Conexion.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, dni);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					double s = rs.getDouble("sueldo");
					return rs.wasNull() ? null : s;
				}
			}
		}
		return null;
	}

	public List<Empleado> buscar(String criterio) throws SQLException {
		List<Empleado> lista = new ArrayList<>();
		String sql = "SELECT e.dni, e.nombre, e.sexo, e.categoria, e.anyos, n.sueldo "
				+ "FROM empleados e LEFT JOIN nominas n ON e.dni = n.dni "
				+ "WHERE e.nombre LIKE ? OR e.dni LIKE ? OR e.categoria LIKE ? OR e.sexo LIKE ?";
		try (Connection c = Conexion.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

			String like = "%" + criterio + "%";
			ps.setString(1, like);
			ps.setString(2, like);
			ps.setString(3, like);
			ps.setString(4, like);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					double sueldo = rs.getObject("sueldo") == null ? 0.0 : rs.getDouble("sueldo");
					Empleado e = new Empleado(rs.getString("dni"), rs.getString("nombre"), rs.getString("sexo"),
							rs.getInt("categoria"), rs.getInt("anyos"), sueldo);
					lista.add(e);
				}
			}
		}
		return lista;
	}

	public boolean actualizarEmpleado(Empleado emp) throws SQLException {
		Connection c = null;
		PreparedStatement psUpdateEmp = null;
		PreparedStatement psUpdateNom = null;
		PreparedStatement psInsertNom = null;
		ResultSet rs = null;

		String sqlUpdateEmp = "UPDATE empleados SET nombre = ?, sexo = ?, categoria = ?, anyos = ? WHERE dni = ?";
		String sqlUpdateNom = "UPDATE nominas SET sueldo = ? WHERE dni = ?";
		String sqlInsertNom = "INSERT INTO nominas(dni, sueldo) VALUES(?, ?)";
		String sqlCheckNom = "SELECT id FROM nominas WHERE dni = ?";

		try {
			c = Conexion.getConnection();
			c.setAutoCommit(false);

			psUpdateEmp = c.prepareStatement(sqlUpdateEmp);
			psUpdateEmp.setString(1, emp.getNombre());
			psUpdateEmp.setString(2, emp.getSexo());
			psUpdateEmp.setInt(3, emp.getCategoria());
			psUpdateEmp.setInt(4, emp.getAnyos());
			psUpdateEmp.setString(5, emp.getDni());
			int rowsEmp = psUpdateEmp.executeUpdate();

			double nuevoSueldo = calcularSueldo(emp.getCategoria(), emp.getAnyos());

			PreparedStatement psCheck = c.prepareStatement(sqlCheckNom);
			psCheck.setString(1, emp.getDni());
			rs = psCheck.executeQuery();
			boolean existeNomina = rs.next();
			rs.close();
			psCheck.close();

			if (existeNomina) {
				psUpdateNom = c.prepareStatement(sqlUpdateNom);
				psUpdateNom.setDouble(1, nuevoSueldo);
				psUpdateNom.setString(2, emp.getDni());
				psUpdateNom.executeUpdate();
			} else {
				psInsertNom = c.prepareStatement(sqlInsertNom);
				psInsertNom.setString(1, emp.getDni());
				psInsertNom.setDouble(2, nuevoSueldo);
				psInsertNom.executeUpdate();
			}

			c.commit();
			return rowsEmp > 0;
		} catch (SQLException ex) {
			if (c != null)
				c.rollback();
			throw ex;
		} finally {
			if (rs != null)
				rs.close();
			if (psUpdateEmp != null)
				psUpdateEmp.close();
			if (psUpdateNom != null)
				psUpdateNom.close();
			if (psInsertNom != null)
				psInsertNom.close();
			if (c != null) {
				c.setAutoCommit(true);
				c.close();
			}
		}
	}

	private double calcularSueldo(int categoria, int anyos) {
		double base;
		switch (categoria) {
		case 1:
			base = 1000;
			break;
		case 2:
			base = 1400;
			break;
		case 3:
			base = 1800;
			break;
		case 4:
			base = 2300;
			break;
		default:
			base = 900;
			break;
		}
		return Math.round((base + anyos * 25) * 100.0) / 100.0;
	}
}
