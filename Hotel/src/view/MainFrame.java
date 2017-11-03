package view;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

public class MainFrame extends JFrame {

	JTable table;
	Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();

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

		JButton btMediaReservas = new JButton("Média de reservas");
		btMediaReservas.setPreferredSize(new Dimension(200, 50));

		btMediaReservas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new DialogMediaReservas();
			}
		});
		panel.add(btMediaReservas, new GBC(0, 0));

		JButton btMediaNoites = new JButton("Média de noites");
		btMediaNoites.setPreferredSize(new Dimension(200, 50));
		btMediaNoites.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new DialogMediaNoites();
			}
		});
		panel.add(btMediaNoites, new GBC(0, 1));

		JButton btMediaDiaria = new JButton("Média de diárias");
		btMediaDiaria.setPreferredSize(new Dimension(200, 50));
		btMediaDiaria.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new DialogMediaDiaria();
			}
		});
		panel.add(btMediaDiaria, new GBC(0, 2));

		JButton btMediaGastos = new JButton("Média de gastos extras");
		btMediaGastos.setPreferredSize(new Dimension(200, 50));
		btMediaGastos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new DialogMediaGastos();
			}
		});
		panel.add(btMediaGastos, new GBC(0, 3));

		JButton btMediaGastosTotal = new JButton("Média de Gastos Total");
		btMediaGastosTotal.setPreferredSize(new Dimension(200, 50));
		btMediaGastosTotal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new DialogGastosTotal();
			}
		});
		panel.add(btMediaGastosTotal, new GBC(0, 4));

		JButton btMediaPessoas = new JButton("Média de Pessoas");
		btMediaPessoas.setPreferredSize(new Dimension(200, 50));
		btMediaPessoas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new DialogMediaPessoas();
			}
		});
		panel.add(btMediaPessoas, new GBC(0, 5));

		JButton btMediaOcupacao = new JButton("Média de Ocupação");
		btMediaOcupacao.setPreferredSize(new Dimension(200, 50));
		btMediaOcupacao.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new DialogOcupacaoMedia();
			}
		});
		panel.add(btMediaOcupacao, new GBC(0, 6));

		add(panel, new GBC(0, 0));
	}

	private void setIconImage() {
		URL iconUrl = getClass().getResource("/images/logo.png");
		ImageIcon icon = new ImageIcon(iconUrl);
		setIconImage(icon.getImage());
	}

	// public void setAcessibilidade() {
	// addKeyListener(new KeyListener() {
	//
	// @Override
	// public void keyTyped(KeyEvent e) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// @Override
	// public void keyReleased(KeyEvent e) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// @Override
	// public void keyPressed(KeyEvent e) {
	// if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
	// setVisible(false);
	// }
	//
	// }
	// });
	// }

}
