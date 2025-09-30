package Laboral;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class ControladorNominas {

    // Alta de un solo empleado (BD + backup)
    public static void altaEmpleado(Empleado e) {
        BDNominas.altaEmpleado(e); // Guardar en la base de datos

        try {
            // Guardar datos en archivo de texto
            try (FileWriter fw = new FileWriter("datos/empleadosBackup.txt", true)) {
                fw.write(e.nombre + "," + e.getCategoria() + "," + e.anyo + "," + e.DNI + "," + e.sexo + "\n");
            }

            // Guardar sueldo en archivo binario
            List<Empleado> lista = new ArrayList<>();
            lista.add(e);
            GestionNominas.escribirSueldosBinario("datos/sueldosBackup.dat", lista);

            System.out.println("Empleado respaldado en ficheros.");
        } catch (Exception ex) {
            System.err.println("Error en backup: " + ex.getMessage());
        }
    }

    // Alta de varios empleados desde un fichero
    public static void altaEmpleado(String rutaFichero) {
        try {
            List<Empleado> empleados = GestionNominas.leerEmpleadosDeFichero(rutaFichero);
            for (Empleado e : empleados) {
                altaEmpleado(e); // Dar de alta cada empleado
            }
        } catch (Exception ex) {
            System.err.println("Error al dar de alta empleados desde fichero: " + ex.getMessage());
        }
    }
}
