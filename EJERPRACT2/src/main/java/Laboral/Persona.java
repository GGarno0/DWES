package Laboral;

public class Persona {

    //Atributos
    public String nombre;
    public String DNI;
    public char sexo;

    //Constructores
    public Persona(String nombre, String DNI, char sexo) {
        this.nombre = nombre;
        this.DNI = DNI;
        this.sexo = sexo;
    }

    public Persona(String nombre, char sexo) {
        this.nombre = nombre;
        this.sexo = sexo;
    }

    //Métodos
    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public void Imprime() {
        System.out.println("Nombre: " + nombre
                + "\nDNI :" + DNI);
    }

}
