package br.quixada.ufc.si.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class AdmMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdmMenu frame = new AdmMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdmMenu() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AdmMenu.class.getResource("/imagens/Steam-icon.png")));
		// setIconImage(Toolkit.getDefaultToolkit().getImage(AdmMenu.class.getResource("/imagens/Steam-icon.png")));
		setTitle("Menu administrador");
		setResizable(false);
		setBackground(new Color(51, 51, 51));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 894, 631);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 51, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(255, 255, 255));
		menuBar.setBounds(0, 0, 888, 31);
		this.setLocationRelativeTo(null);

		JLabel label = new JLabel("");
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label.setIcon(new ImageIcon(AdmMenu.class.getResource("/imagens/Steam-icon.png")));
		label.setBounds(171, 133, 506, 465);
		label.setVisible(true);

		JLabel lblSteam = new JLabel("Steam");
		lblSteam.setHorizontalAlignment(SwingConstants.CENTER);
		lblSteam.setForeground(Color.WHITE);
		lblSteam.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 80));
		lblSteam.setBounds(0, 25, 888, 128);
		contentPane.add(lblSteam);
		contentPane.add(label);

		contentPane.add(menuBar);

		JMenu mnJogos = new JMenu("          Jogos          ");
		mnJogos.setHorizontalAlignment(SwingConstants.CENTER);
		mnJogos.setFont(new Font("Arial", Font.BOLD, 14));
		mnJogos.setBackground(new Color(255, 255, 255));
		menuBar.add(mnJogos);

		JButton btCadastrarJogos = new JButton(" Cadastrar Jogos ");
		btCadastrarJogos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				AdmCadastroJogos obj2 = new AdmCadastroJogos();
				obj2.setVisible(true);

			}
		});
		mnJogos.add(btCadastrarJogos);
		btCadastrarJogos.setFont(new Font("Arial", Font.PLAIN, 14));
		btCadastrarJogos.setBackground(new Color(255, 255, 255));

		JButton btnNewButton = new JButton("  Remover Jogos  ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				AdmRemoverJogos obj = new AdmRemoverJogos();
				obj.setVisible(true);

			}
		});
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 14));
		mnJogos.add(btnNewButton);

		JButton btnListarJogos = new JButton("     Listar Jogos     ");
		btnListarJogos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdmListarJogos obj = new AdmListarJogos();
				obj.setVisible(true);

			}
		});
		btnListarJogos.setBackground(new Color(255, 255, 255));
		btnListarJogos.setFont(new Font("Arial", Font.PLAIN, 14));
		mnJogos.add(btnListarJogos);

		JMenu mnClientes = new JMenu("          Clientes          ");
		mnClientes.setFont(new Font("Arial", Font.BOLD, 14));
		menuBar.add(mnClientes);

		JButton btRemoverClientes = new JButton("Remover Clientes");
		btRemoverClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdmRemoverCliente obj = new AdmRemoverCliente();
				obj.setVisible(true);
			}
		});
		mnClientes.add(btRemoverClientes);
		btRemoverClientes.setBackground(new Color(255, 255, 255));
		btRemoverClientes.setFont(new Font("Arial", Font.PLAIN, 14));

		JButton btRelatorio = new JButton("Relatorio de compras");
		btRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				AdmRelatorioCompras rc = new AdmRelatorioCompras();
				rc.setVisible(true);

			}
		});
		btRelatorio.setIcon(new ImageIcon(AdmMenu.class.getResource("/imagens/application_form.png")));
		btRelatorio.setFont(new Font("Arial", Font.BOLD, 14));
		btRelatorio.setBackground(new Color(255, 255, 255));
		menuBar.add(btRelatorio);

		JButton btDeslogarAdm = new JButton("Deslogar");
		btDeslogarAdm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdmMenu obj = new AdmMenu();
				obj.setVisible(false);
				dispose();
			}
		});
		btDeslogarAdm.setIcon(new ImageIcon(AdmMenu.class.getResource("/imagens/status_busy.png")));
		btDeslogarAdm.setBackground(new Color(255, 255, 255));
		btDeslogarAdm.setFont(new Font("Arial", Font.BOLD, 14));
		menuBar.add(btDeslogarAdm);
	}
}
