package view;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import control.Operacoes;

public class DialogMediaPessoas extends JDialog {
	
	private JTable table;
	/**
	 * {@code JPanel} que montará o layout da {@code JDialog}.
	 */
	private JPanel panel = new JPanel(new GridBagLayout());

	/**
	 * Construtor da classe.
	 */
	public DialogMediaPessoas() {
		buildDialog();
	}

	/**
	 * Método que posiciona e chama os outros método responsaveis pelo contúdo da
	 * {@code JDialog}.
	 */
	private void buildDialog() {
		setLayout(new GridBagLayout());
		setLocationRelativeTo(null);
		setTitle("M�dia de Diaria");
		buildMediaAnual();
		buildTable();
		buildBotaoVoltar();
		setSize(200, 300);
		setMinimumSize(new Dimension(200, 300));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(true);
		setModal(true);
		setVisible(true);
	}

	/**
	 * Método que cria e exibe a média anual de diárias.
	 */
	private void buildMediaAnual() {
		JLabel labelMediaGastosExtras = new JLabel("M�dia Pessoas: ");
		panel.add(labelMediaGastosExtras, new GBC(0, 0));
		JTextField tfMediaExtras = new JTextField(20);
		// Chamando o método que faz as contas para pegar o valor anual.
		tfMediaExtras.setText(Operacoes.mediaPessoasAnual());
		tfMediaExtras.setEditable(false);
		panel.add(tfMediaExtras, new GBC(1, 0).horizontal());
		add(panel, new GBC(0, 0));
	}

	/**
	 * Método que constrói e popula a tabela.
	 */
	private void buildTable() {
		// Instanciando a Table
		table = new JTable();
		// Declaro e instancio o modelo da tabela.
		OutputTableModel opTableModel = new OutputTableModel();
		// Chama o método para popular a tabela. Perceba que na passagem de parametro
		// eu
		// chamo outro método.
		opTableModel.adicionaTableModel(Operacoes.mediaPessoaMes());
		// Setando o modelo que ela terá.
		table.setModel(opTableModel);
		// Instanciando um JScrollPane e setando a tabela em seu contrutor para que o
		// JScrollPane saiba com quem operar.
		JScrollPane scrollPane = new JScrollPane(table);
		// Adicionando o JScrollPane no JPanel. ATENÇÃO: JScrollPane e JPanel são
		// coisas
		// diferentes.
		panel.add(scrollPane, new GBC(0, 1).both().gridwh(2, 1));
		// Adiconando o JPanel na JDialog.
		add(panel, new GBC(0, 1).both());
	}

	/**
	 * Método que constrói o {@code JButton} de voltar.
	 */
	private void buildBotaoVoltar() {
		JButton botaoVoltar = new JButton("Voltar");
		botaoVoltar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Esse método é da própria JDialog e ele apenas serve para destruir a
				// JDialog.
				dispose();
			}
		});
		panel.add(botaoVoltar, new GBC(1, 2).right());
		add(panel, new GBC(0, 2).both());
	}
}
