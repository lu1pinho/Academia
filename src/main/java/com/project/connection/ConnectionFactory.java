package com.project.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionFactory {

    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost/bd_usuario", "root", "2027");
        } catch (SQLException excecao) {
            throw new RuntimeException(excecao);
        }
    }
}
