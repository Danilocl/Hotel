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
import javax.swing.SwingConstants;

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
		setSize(300, 200);
		setMinimumSize(new Dimension(200, 200));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		pack();

	}

	private void buildButtonPanel() {

		JPanel panel = new JPanel(new GridBagLayout());

		JButton btMediaReservas = new JButton("M�dia de reservas");
		btMediaReservas.setPreferredSize(new Dimension(200,50));
		
		
		btMediaReservas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new DialogMediaReservas();
			}
		});
		panel.add(btMediaReservas, new GBC(0, 0));

		JButton btMediaNoites = new JButton("M�dia de noites");
		btMediaNoites.setPreferredSize(new Dimension(200,50));
		btMediaNoites.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new DialogMediaNoites();
			}
		});
		panel.add(btMediaNoites, new GBC(0, 1));

		JButton btMediaDiaria = new JButton("M�dia de di�rias");
		btMediaDiaria.setPreferredSize(new Dimension(200,50));
		btMediaDiaria.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new DialogMediaDiaria();
			}
		});
		panel.add(btMediaDiaria, new GBC(0, 2));

		JButton btMediaGastos = new JButton("M�dia de gastos extras");
		btMediaGastos.setPreferredSize(new Dimension(200,50));
		btMediaGastos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new DialogMediaGastos();
			}
		});
		panel.add(btMediaGastos, new GBC(0, 3));

		add(panel, new GBC(0, 0));
	}

}
