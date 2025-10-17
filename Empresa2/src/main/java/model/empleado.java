package model;

public class empleado {

	private String nombre;
	private String dni;
	private char sexo;
	private int categoria;
	private int antiguedad;
	
	public empleado(String nombre, String dni, char sexo, int categoria, int antiguedad) {
		super();
		this.nombre = nombre;
		this.dni = dni;
		this.sexo = sexo;
		this.categoria = categoria;
		this.antiguedad = antiguedad;
	}
	
	public empleado() {
		// TODO Auto-generated constructor stub
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}

	public int getAntiguedad() {
		return antiguedad;
	}

	public void setAntiguedad(int antiguedad) {
		this.antiguedad = antiguedad;
	}
	@Override
	public String toString() {
		return "empleado [nombre=" + nombre + ", dni=" + dni + ", sexo=" + sexo + ", categoria=" + categoria
				+ ", antiguedad=" + antiguedad + "]";
	}
}
	

