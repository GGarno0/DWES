package model;

public class Empleado {
	private String dni;
	private String nombre;
	private String sexo;
	private int categoria; // ahora int
	private int anyos; // años trabajados -> columna anyos
	private double sueldo; // viene de tabla nominas

	public Empleado() {
	}

	public Empleado(String dni, String nombre, String sexo, int categoria, int anyos, double sueldo) {
		this.dni = dni;
		this.nombre = nombre;
		this.sexo = sexo;
		this.categoria = categoria;
		this.anyos = anyos;
		this.sueldo = sueldo;
	}

	// getters / setters
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}

	public int getAnyos() {
		return anyos;
	}

	public void setAnyos(int anyo) {
		this.anyos = anyo;
	}

	public double getSueldo() {
		return sueldo;
	}

	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}
}