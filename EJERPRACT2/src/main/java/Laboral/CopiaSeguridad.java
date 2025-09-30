package Laboral;

import java.io.*;
import java.sql.*;

public class CopiaSeguridad {

    // Parámetros de conexión a la base de datos MySQL
    private static final String URL = "jdbc:mysql://localhost:3307/nominas?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASS = "123456";

    // Método para realizar copia de seguridad de empleados y sueldos
    public static void realizarBackup() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {

            // Exportar datos de empleados
            PrintWriter pw = new PrintWriter(new FileWriter("empleadosBackup.txt"));
            String sql = "SELECT * FROM Empleados"; // Consultar todos los empleados
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                // Guardar cada empleado en el archivo de texto separado por ";"
                pw.println(rs.getString("dni") + ";"
                        + rs.getString("nombre") + ";"
                        + rs.getString("sexo") + ";"
                        + rs.getInt("categoria") + ";"
                        + rs.getInt("anyos"));
            }
            pw.close(); // Cerrar archivo de texto

            // Exportar sueldos a archivo binario
            DataOutputStream dos = new DataOutputStream(new FileOutputStream("sueldosBackup.dat"));
            sql = "SELECT * FROM Nominas"; // Consultar todos los sueldos
            rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                // Guardar dni y sueldo en archivo binario
                dos.writeUTF(rs.getString("dni"));
                dos.writeDouble(rs.getDouble("sueldo"));
            }
            dos.close(); // Cerrar archivo binario

            // Mensaje de éxito
            System.out.println("Copia de seguridad realizada con éxito.");
        } catch (Exception ex) {
            // Capturar errores de conexión o escritura de archivos
            System.err.println("Error al realizar backup: " + ex.getMessage());
        }
    }
}
