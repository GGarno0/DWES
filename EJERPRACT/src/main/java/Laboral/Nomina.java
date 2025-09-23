package Laboral;

public class Nomina {

    private static final int SUELDO_BASE[]
            = {50000, 70000, 90000, 110000, 130000,
                150000, 170000, 190000, 210000, 230000};

    //Métodos
    public static int sueldo(Empleado e) {
        return SUELDO_BASE[e.getCategoria() - 1] + 5000 * e.anyo;    //Suedo[1-1](Array)+5000*X(anyo)     *Esta categoria iniciada en 1
    }

}
