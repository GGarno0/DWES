package Laboral;

import java.util.Scanner;

public class MenuNominas {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);  // Scanner para leer entradas del usuario
        int opcion = 0;

        // Bucle principal del menú
        do {
            // Mostrar opciones del menú
            System.out.println("\n=== GESTIÓN DE NÓMINAS ===");
            System.out.println("1. Mostrar todos los empleados");
            System.out.println("2. Mostrar salario de un empleado");
            System.out.println("3. Modificar datos de un empleado");
            System.out.println("4. Recalcular sueldo de un empleado");
            System.out.println("5. Recalcular sueldos de todos los empleados");
            System.out.println("6. Realizar copia de seguridad");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            // Procesar la opción seleccionada
            switch (opcion) {
                case 1:
                    // Mostrar todos los empleados de la base de datos
                    BDNominas.mostrarEmpleados();
                    break;
                case 2:
                    // Mostrar sueldo de un empleado específico
                    System.out.print("Introduce DNI: ");
                    String dni = sc.nextLine();
                    BDNominas.mostrarSueldoEmpleado(dni);
                    break;
                case 3:
                    // Llamar al submenú para modificar datos de un empleado
                    submenuModificar(sc);
                    break;
                case 4:
                    // Recalcular sueldo de un empleado
                    System.out.print("Introduce DNI: ");
                    dni = sc.nextLine();
                    BDNominas.recalcularSueldoEmpleado(dni);
                    break;
                case 5:
                    // Recalcular sueldos de todos los empleados
                    BDNominas.recalcularSueldosTodos();
                    break;
                case 6:
                    // Realizar copia de seguridad de la base de datos
                    CopiaSeguridad.realizarBackup();
                    break;
                case 0:
                    // Salir del programa
                    System.out.println("Saliendo...");
                    break;
                default:
                    // Opción no válida
                    System.out.println("Opción no válida");
            }
        } while (opcion != 0);

        sc.close();  // Cerrar Scanner
    }

    // Submenú para modificar datos de un empleado
    private static void submenuModificar(Scanner sc) {
        System.out.println("\n=== MODIFICAR EMPLEADO ===");
        System.out.print("Introduce DNI del empleado: ");
        String dni = sc.nextLine();

        // Elegir qué campo modificar
        System.out.println("¿Qué deseas modificar?");
        System.out.println("1. Nombre");
        System.out.println("2. Categoría");
        System.out.println("3. Años trabajados");
        System.out.print("Opción: ");
        int op = sc.nextInt();
        sc.nextLine(); // limpiar buffer

        System.out.print("Nuevo valor: ");
        String nuevo = sc.nextLine();

        // Llamar al método de BDNominas para modificar el empleado
        BDNominas.modificarEmpleado(dni, op, nuevo);
    }
}
