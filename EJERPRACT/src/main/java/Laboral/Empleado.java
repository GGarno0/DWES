package Laboral;

public class Empleado extends Persona {

    //Atributos
    private int categoria;
    public int anyo;

    //Constructores
    public Empleado(int categoria, int anyo, String nombre, String DNI, char sexo) throws DatosNoCorrectosException {
        super(nombre, DNI, sexo);
        if (categoria < 1 || categoria > 10) {
            throw new DatosNoCorrectosException("Categoría debe estar entre 1 y 10");
        }
        if (anyo < 0) {
            throw new DatosNoCorrectosException("Anyos trabajados no pueden ser negativos");
        }
        if (sexo != 'M' && sexo != 'F') {
            throw new DatosNoCorrectosException("Sexo debe ser 'M' o 'F'");
        }
        this.categoria = categoria;
        this.anyo = anyo;
    }

    public Empleado(String nombre, String DNI, char sexo) throws DatosNoCorrectosException {
        this(1, 0, nombre, DNI, sexo);
    }

    //Métodos
    public void setCategoria(int categoria) throws DatosNoCorrectosException {
        if (categoria < 1 || categoria > 10) {
            throw new DatosNoCorrectosException("Categoría debe estar entre 1 y 10");
        }
        this.categoria = categoria;
    }

    public int getCategoria() {
        return categoria;
    }

    public void incrAnyo() {
        this.anyo++;        //incrementa en 1
    }

    public void imprime() {
        System.out.println("Nombre:" + nombre
                + "\nDNI:" + DNI
                + "\nSexo:" + sexo
                + "\nCategoria:" + getCategoria()
                + "\nAnyo:" + anyo);
    }

}
