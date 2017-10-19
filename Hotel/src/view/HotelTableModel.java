// package view;
//
// import java.util.ArrayList;
// import java.util.List;
//
// import model.Aluno;
//
// public class HotelTableModel {
//
// private String[] columnNames = { "Nome", "CPF", "Telefone", "Endereço",
// "Email" };
//
// private List<Aluno> alunos = new ArrayList<>();
//
// @Override
// public int getColumnCount() {
// return columnNames.length;
// }
//
// @Override
// public Object getValueAt(int rowIndex, int columnIndex) {
// Aluno aluno = alunos.get(rowIndex);
// switch (columnIndex) {
// case 0:
// return aluno.getNome();
// case 1:
// return aluno.getCpf();
// case 2:
// return aluno.getCelular();
// case 3:
// return aluno.getEndereco();
// case 4:
// return aluno.getEmail();
// }
// return "";
// }
//
// @Override
// public String getColumnName(int column) {
// return columnNames[column];
// }
//
// @Override
// public boolean isCellEditable(int row, int column) {
// return false;
// }
//
// /**
// * Recupera um aluno com base na numeração da linha.
// *
// * @param rowNumber
// * Número da linha.
// * @return aluno.
// */
// public Aluno getRow(int rowNumber) {
// return alunos.get(rowNumber);
// }
//
// /**
// * Adiciona um aluno.
// *
// * @param aluno
// * Aluno.
// */
//
// /**
// * Atualiza um aluno.
// *
// * @param aluno
// * Aluno.
// * @param selectedRow
// * Linha selecionada pelo usuário.
// */
//
// this.setValueAt(aluno.getNome(), selectedRow, 0);
//
// this.setValueAt(aluno.getCpf(), selectedRow, 2);
//
// this.setValueAt(aluno.getCelular(), selectedRow, 4);
//
// this.setValueAt(aluno.getEndereco(), selectedRow, 6);
//
// this.setValueAt(aluno.getEmail(), selectedRow, 8);
// }
//
// /**
// * Remove um aluno.
// *
// * @param selectedRow
// * Linha selecionada pelo usuário.
// */
// }
