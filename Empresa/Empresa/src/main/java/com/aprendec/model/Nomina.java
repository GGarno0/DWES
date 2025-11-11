package com.aprendec.model;

public class Nomina {
    private String empleado_dni;
    private double sueldo;

    public Nomina() {}

    public Nomina(String empleadoDni, double sueldo) {
        this.empleado_dni = empleadoDni;
        this.sueldo = sueldo;
    }

    // Getters y Setters
    public String getEmpleadoDni() {
        return empleado_dni;
    }

    public void setEmpleadoDni(String empleadoDni) {
        this.empleado_dni = empleadoDni;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    @Override
    public String toString() {
        return "Nomina{" +
                "empleadoDni='" + empleado_dni + '\'' +
                ", sueldo=" + sueldo +
                '}';
    }
}
