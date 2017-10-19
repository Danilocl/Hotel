package view;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class MainFrame extends JFrame {
	
	JTable table;
	
	public static void main(String[] args) {
		new MainFrame();
	}

	public MainFrame() {
		build();
	}

	private void build() {
		setLayout(new GridBagLayout());
		buildButtonPanel();
		// buildTable();
		setSize(300, 200);
		setMinimumSize(new Dimension(400, 300));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		// setIconImage();
	}

	private void buildTable() {
		JPanel panel = new JPanel(new GridBagLayout());
		table = new JTable();
		// table.setModel(new HotelTableModel());
		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane, new GBC(0, 1).gridwh(2, 1).both());
		add(panel, new GBC(0, 0).both().insets(10, 5, 5, 0));
	}

	private void buildButtonPanel() {

		JPanel panel = new JPanel(new GridBagLayout());
		JButton gerar = new JButton("Gerar");
		panel.add(gerar, new GBC(0, 1).top().horizontal());

		gerar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		add(panel, new GBC(1, 0).insets(5, 0, 7, 6));
	}

}
