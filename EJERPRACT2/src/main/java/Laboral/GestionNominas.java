package Laboral;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestionNominas {

    // Leer empleados desde un fichero
    public static List<Empleado> leerEmpleadosDeFichero(String rutaFichero) throws IOException, DatosNoCorrectosException {
        List<Empleado> empleados = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaFichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 5) {
                    String nombre = datos[0].trim();
                    int categoria = Integer.parseInt(datos[1].trim());
                    int anyo = Integer.parseInt(datos[2].trim());
                    String dni = datos[3].trim();
                    char sexo = datos[4].trim().charAt(0);

                    Empleado emp = new Empleado(nombre, categoria, anyo, dni, sexo);
                    empleados.add(emp);
                } else {
                    System.err.println("Línea con formato incorrecto: " + linea);
                }
            }
        }
        return empleados;
    }

    // Calcular sueldo de un empleado
    public static double calcularSueldo(Empleado e) {
        // Sueldo base = 1000 + categoria*200 + anyo*50
        return 1000 + e.getCategoria() * 200 + e.anyo * 50;
    }

    // Escribir sueldos en fichero binario
    public static void escribirSueldosBinario(String rutaSalida, List<Empleado> empleados) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(rutaSalida))) {
            for (Empleado e : empleados) {
                dos.writeUTF(e.DNI);
                dos.writeDouble(calcularSueldo(e));
            }
        }
    }

    // Método principal para probar lectura y escritura
    public static void main(String[] args) {
        String rutaEntrada = "datos/empleados.txt";
        String rutaSalida = "datos/sueldos.dat";

        try {
            List<Empleado> empleados = leerEmpleadosDeFichero(rutaEntrada);
            escribirSueldosBinario(rutaSalida, empleados);
            System.out.println("Archivo de sueldos creado con éxito.");
        } catch (IOException | DatosNoCorrectosException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
