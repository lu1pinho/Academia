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

        // Adicionando um listener para detectar mudanças na seleção da tabela
        tabela.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tabela.getSelectedRow();
                if (selectedRow != -1) {
                    String nome = (String) modelo.getValueAt(selectedRow, 0);
                    String cpf = (String) modelo.getValueAt(selectedRow, 1);
                    String nascimento = (String) modelo.getValueAt(selectedRow, 2);
                    String email = (String) modelo.getValueAt(selectedRow, 3);
                    String telefone = (String) modelo.getValueAt(selectedRow, 4);
                    String plano = (String) modelo.getValueAt(selectedRow, 5);

                    // Buscar o aluno original no banco de dados para manter os campos inalterados
                    Aluno alunoOriginal;
                    try {
                        alunoOriginal = alunoDAO.buscarAluno(cpf);
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao buscar aluno original: " + ex.getMessage());
                        return;
                    }

                    // Atualiza o Aluno com as novas informações sem alterar status, data inicial e data final
                    Aluno alunoAtualizado = new Aluno(
                            cpf, nome, nascimento, email, telefone, plano,
                            alunoOriginal.getStatus(), alunoOriginal.getData_inicio(), alunoOriginal.getData_fim()
                    );
                    try {
                        alunoDAO.atualizarAluno(alunoAtualizado);
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao atualizar aluno: " + ex.getMessage());
                    }
                }
            }
        });
    }
}