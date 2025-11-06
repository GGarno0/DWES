package com.aprendec.conexion;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

/*
 *  Singleton
 */

public final class Conexion {
    // Esta es la única instancia que va a existir en todo el programa
    private static BasicDataSource instance;

    // Constructor privado para que nadie pueda crear nuevas instancias
    private Conexion() {
    }

    // Este método devuelve siempre la misma instancia
    public static DataSource getInstance() {
        // Si no existe la instancia, la creamos
        if (instance == null) {
            instance = inicializarDataSource();
        }
        // Devolvemos la instancia
        return instance;
    }

    // Aquí configuramos la conexión a la base de datos
    private static BasicDataSource inicializarDataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUsername("root");
        ds.setPassword("root");
        ds.setUrl("jdbc:mysql://localhost:3306/nominas?useTimezone=true&serverTimezone=UTC");
        ds.setInitialSize(5);
        ds.setMaxIdle(10);
        ds.setMaxTotal(20);
        return ds;
    }

    // Método para obtener una conexión de la base de datos
    public static Connection getConnection() throws SQLException {
        return getInstance().getConnection();
    }
}