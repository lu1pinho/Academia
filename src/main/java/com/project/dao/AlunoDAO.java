package com.project.dao;

import com.project.connection.ConnectionFactory;
import com.project.ui.Aluno;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    public boolean existeAluno(String cpf, String email, String telefone) {
        String sql = "SELECT 1 FROM usuario WHERE cpf = ? OR email = ? OR telefone = ?";
        try (Connection con = new ConnectionFactory().getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            stmt.setString(2, email);
            stmt.setString(3, telefone);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void salvar(Aluno aluno) throws SQLException {

        String sql = "INSERT INTO usuario (nomecompleto, cpf, datanascimento, telefone, email, plano, status, datainicial, datafinal) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = new ConnectionFactory().getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getCpf());
            stmt.setString(3, aluno.getNascimento());
            stmt.setString(4, aluno.getTelefone());
            stmt.setString(5, aluno.getEmail());
            stmt.setString(6, aluno.getPlano());
            stmt.setString(7, aluno.getStatus());
            stmt.setString(8, aluno.getData_inicio());
            stmt.setString(9, aluno.getData_fim());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao salvar aluno: " + e.getMessage());
        }
    }

    public Aluno buscarAluno(String cpf) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE cpf = ?";
        Aluno aluno = null;

        try (Connection con = new ConnectionFactory().getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, cpf);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    aluno = new Aluno(
                            rs.getString("nomecompleto"),
                            rs.getString("cpf"),
                            rs.getString("datanascimento"),
                            rs.getString("email"),
                            rs.getString("telefone"),
                            rs.getString("plano"),
                            rs.getString("status"),
                            rs.getString("datainicial"),
                            rs.getString("datafinal")
                    );
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar aluno: " + e.getMessage());
        }

        if (aluno == null) {
            throw new SQLException("Aluno não encontrado com o CPF informado.");
        }

        return aluno;
    }


    public void deletarAluno(String cpf) throws SQLException {
        String sql = "DELETE FROM usuario WHERE cpf = ?";
        try (Connection con = new ConnectionFactory().getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao deletar aluno: " + e.getMessage());
        }
    }

    public List<Aluno> buscarTodosAlunos() throws SQLException {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM usuario";

        try (Connection con = new ConnectionFactory().getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Aluno aluno = new Aluno(
                        rs.getString("nomecompleto"),
                        rs.getString("cpf"),
                        rs.getString("datanascimento"),
                        rs.getString("email"),
                        rs.getString("telefone"),
                        rs.getString("plano"),
                        rs.getString("status"),
                        rs.getString("datainicial"),
                        rs.getString("datafinal")
                );
                alunos.add(aluno);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar todos os alunos: " + e.getMessage());
        }
        return alunos;
    }
    
    
    
}
