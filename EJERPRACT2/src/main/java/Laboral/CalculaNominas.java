package Laboral;

public class CalculaNominas {

    //4.3
    private static void escribe(Empleado e1, Empleado e2) {
        e1.Imprime();
        System.out.println("Sueldo: " + Nomina.sueldo(e1));
        e2.Imprime();
        System.out.println("Sueldo: " + Nomina.sueldo(e2));
    }

    public static void main(String[] args) throws DatosNoCorrectosException {
        try {
            //4.1
            Empleado e1 = new Empleado( "James Cosling",4, 7, "32000032G", 'M');
            //4.2
            Empleado e2 = new Empleado("Ada Lovelace", "32000031R", 'F');
            //4.4
            escribe(e1, e2);
            //4.5
            e2.incrAnyo();
            e1.setCategoria(9);
            //4.6 Se imprime de nuevo
            System.out.println("\nDatos Actualizados");
            escribe(e1, e2);
        } catch (DatosNoCorrectosException ex) {
            System.out.println("Datos no correctos");
        }

    }
}
//
