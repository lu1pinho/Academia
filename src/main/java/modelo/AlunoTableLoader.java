package modelo;

import com.project.dao.AlunoDAO;
import com.project.ui.Aluno;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;

public class AlunoTableLoader {

    public void carregarTabela(JTable tabela) {
        AlunoDAO alunoDAO = new AlunoDAO();
        List<Aluno> alunos;

        try {
            alunos = alunoDAO.buscarTodosAlunos();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar alunos: " + e.getMessage());
            return;
        }

        String[] colunas = {"Nome", "CPF", "Data de Nascimento", "Email", "Telefone", "Plano", "Status", "Data Inicial", "Data Final"};
        DefaultTableModel modelo = new AlunoTableModel(colunas, 0);

        for (Aluno aluno : alunos) {
            Object[] linha = {
                    aluno.getNome(),
                    aluno.getCpf(),
                    aluno.getNascimento(),
                    aluno.getEmail(),
                    aluno.getTelefone(),
                    aluno.getPlano(),
                    aluno.getStatus(),
                    aluno.getData_inicio(),
                    aluno.getDatafim()
            };
            modelo.addRow(linha);
        }

        tabela.setModel(modelo);
    }
}