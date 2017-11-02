package view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Hospedagem;
import model.Output;;

public class OutputTableModel extends AbstractTableModel {

	private String[] columnNames = { "Mes", "Valor" };

	private List<Output> outputs = new ArrayList<>();

	public OutputTableModel() {
		// Output o = new Output("Janeiro", "2154.25");
		// outputs.add(o);
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Output aluno = outputs.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return aluno.getMes();
		case 1:
			return aluno.getValor();
		}
		return "";
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	@Override
	public int getRowCount() {
		return outputs.size();
	}

	public void adicionaTableModel(ArrayList<Output> lista) {
		// TODO Fabricio, fazer a adicao na tabela. Metodo vai receber uma lista com
		// todos os meses e vai adicionar na tabela.
		
	}

}
