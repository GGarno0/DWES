package com.aprendec.command;

/**
 * Patrón Factory Method
 * Devuelve la instancia de comando según la acción solicitada.
 */
public class CommandFactory {
    public static Command getCommand(String accion) {
        if (accion == null) return null;

        switch (accion) {
            case "listar" -> {
                return new ListarCommand();
            }
            case "sueldo" -> {
                return new SueldoCommand();
            }
            case "modificar" -> {
                return new ModificarCommand();
            }
            default -> {
                return null;
            }
        }
    }
}
