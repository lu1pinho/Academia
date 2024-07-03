/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.dao;


import com.project.connection.ConnectionFactory;
import com.project.ui.Admin; // Suponho que Admin seja a classe que representa um administrador

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author oluis
 */
public class AdminDAO {

    public boolean existeAdmin(String cpf, String senha) {
        String sql = "SELECT 1 FROM admins WHERE cpf = ? AND senha = ?";
        try (Connection con = new ConnectionFactory().getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            stmt.setString(2, senha);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void salvar(Admin admin) throws SQLException {
        String sql = "INSERT INTO admins (cpf, senha) VALUES (?, ?)";
        try (Connection con = new ConnectionFactory().getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, admin.getCpf());
            stmt.setString(2, admin.getSenha());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao salvar admin: " + e.getMessage());
        }
    }

    public Admin buscarAdmin(String cpf) throws SQLException {
        String sql = "SELECT * FROM admins WHERE cpf = ?";
        Admin admin = null;

        try (Connection con = new ConnectionFactory().getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, cpf);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    admin = new Admin(
                            rs.getString("cpf"),
                            rs.getString("senha")
                    );
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar admin: " + e.getMessage());
        }

        if (admin == null) {
            throw new SQLException("Admin não encontrado com o CPF informado.");
        }

        return admin;
    }

    public void deletarAdmin(String cpf) throws SQLException {
        String sql = "DELETE FROM admins WHERE cpf = ?";
        try (Connection con = new ConnectionFactory().getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao deletar admin: " + e.getMessage());
        }
    }

    public List<Admin> buscarTodosAdmins() throws SQLException {
        List<Admin> admins = new ArrayList<>();
        String sql = "SELECT * FROM admins";

        try (Connection con = new ConnectionFactory().getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Admin admin = new Admin(
                        rs.getString("cpf"),
                        rs.getString("senha")
                );
                admins.add(admin);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar todos os admins: " + e.getMessage());
        }
        return admins;
    }
}