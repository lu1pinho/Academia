package modelo;

import javax.swing.table.DefaultTableModel;

public class AlunoTableModel extends DefaultTableModel {

    public AlunoTableModel(Object[] columnNames, int rowCount) {
        super(columnNames, rowCount);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false; // Impede que as células sejam editáveis
    }
}