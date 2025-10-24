package model;

public class Nomina {

	private String empleado_dni;
	private double sueldo;
	
	 private static final int SUELDO_BASE[]
	            = {50000, 70000, 90000, 110000, 130000,
	                150000, 170000, 190000, 210000, 230000};
	
	public Nomina(String empleado_dni, double sueldo) {
		super();
		this.empleado_dni = empleado_dni;
		this.sueldo = sueldo;
	}
	
	public Nomina() {
	}

	public String getEmpleado_dni() {
		return empleado_dni;
	}

	public void setEmpleado_dni(String empleado_dni) {
		this.empleado_dni = empleado_dni;
	}

	public double getSueldo() {
		return sueldo;
	}

	public void setSueldo(double e) {
		this.sueldo = e;
	}
	
	 //Métodos
    public static int sueldo(Empleado e) {
        return SUELDO_BASE[e.getCategoria() - 1] + 5000 * e.getAnyos();    //Suedo[1-1](Array)+5000*X(anyo)     *Esta categoria iniciada en 1
    }

}
