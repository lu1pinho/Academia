package com.project.connection;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

public class TestConnection {
    public static void main(String[] args) {
        Connection conn = null;

        // Obtém uma conexão utilizando a ConnectionFactory
        conn = new ConnectionFactory().getConnection();
        // Verifica se a conexão foi estabelecida com sucesso
        if (conn != null) {
            System.out.println("Conexão estabelecida com sucesso!");
        } else {
            System.out.println("Falha ao conectar ao banco de dados.");
            JOptionPane.showMessageDialog(null,"Falha ao conectar ao banco de dados.!");
        }
        // Fecha a conexão se ela estiver aberta
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar a conexão: " + e.getMessage());
        }
    }
}
