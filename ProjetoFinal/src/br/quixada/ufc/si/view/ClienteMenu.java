package br.quixada.ufc.si.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.quixada.ufc.si.model.Cliente;

public class ClienteMenu extends JFrame {

	private JPanel contentPane;
	private JTable tabelaJogos;
	private JTextField textField;
	private JTextField textCompra;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cliente c = new Cliente();
					ClienteMenu frame = new ClienteMenu(c);
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
	public ClienteMenu(Cliente cliente_logado) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ClienteMenu.class.getResource("/imagens/Steam-icon.png")));
		setTitle("Menu principal");

		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 956, 702);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		menuBar.setForeground(new Color(255, 255, 255));
		menuBar.setFont(new Font("Dialog", Font.BOLD, 29));
		menuBar.setMargin(new Insets(2, 0, 0, 0));
		menuBar.setBackground(new Color(255, 255, 255));
		this.setLocationRelativeTo(null);
		setJMenuBar(menuBar);

		JMenu mnLoja = new JMenu("LOJA           ");
		mnLoja.setIcon(new ImageIcon(ClienteMenu.class.getResource("/imagens/cart_go.png")));
		mnLoja.setFont(new Font("Arial", Font.PLAIN, 18));
		mnLoja.setForeground(Color.DARK_GRAY);
		menuBar.add(mnLoja);

		JMenuItem mntmComprarJogos = new JMenuItem("Comprar Jogos");
		mntmComprarJogos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ClienteCompraJogos obj = new ClienteCompraJogos(cliente_logado);
				obj.setVisible(true);

			}
		});
		mntmComprarJogos.setFont(new Font("Arial", Font.BOLD, 14));
		mntmComprarJogos.setForeground(new Color(64, 64, 64));
		mntmComprarJogos.setBackground(new Color(255, 255, 255));
		mnLoja.add(mntmComprarJogos);

		JMenu mnMinhaLoja = new JMenu("BIBLIOTECA           ");
		mnMinhaLoja.setIcon(new ImageIcon(ClienteMenu.class.getResource("/imagens/controller.png")));
		mnMinhaLoja.setFont(new Font("Arial", Font.PLAIN, 18));
		mnMinhaLoja.setForeground(Color.DARK_GRAY);
		menuBar.add(mnMinhaLoja);

		JMenuItem mntmMeusJogos = new JMenuItem("Meus Jogos");
		mntmMeusJogos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ClienteListarJogos obj = new ClienteListarJogos(cliente_logado.getCpf());
				obj.setVisible(true);

			}
		});
		mntmMeusJogos.setFont(new Font("Arial", Font.BOLD, 14));
		mntmMeusJogos.setForeground(Color.DARK_GRAY);
		mntmMeusJogos.setBackground(new Color(255, 255, 255));
		mnMinhaLoja.add(mntmMeusJogos);

		JMenu mnPerfil = new JMenu("PERFIL           ");
		mnPerfil.setIcon(new ImageIcon(ClienteMenu.class.getResource("/imagens/user.png")));
		mnPerfil.setFont(new Font("Arial", Font.PLAIN, 18));
		mnPerfil.setForeground(Color.DARK_GRAY);
		menuBar.add(mnPerfil);

		JMenuItem mntmInformaes = new JMenuItem("Informa\u00E7\u00F5es");
		mntmInformaes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				JOptionPane.showMessageDialog(null,
						"Nome:    " + cliente_logado.getNome() + "\nLogin:    " + cliente_logado.getLogin());

			}
		});
		mntmInformaes.setFont(new Font("Arial", Font.BOLD, 14));
		mntmInformaes.setForeground(Color.DARK_GRAY);
		mntmInformaes.setBackground(new Color(255, 255, 255));
		mnPerfil.add(mntmInformaes);

		JMenuItem mntmAtualizarInformaes = new JMenuItem("Atualizar dados");
		mntmAtualizarInformaes.setBackground(new Color(255, 255, 255));
		mntmAtualizarInformaes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ClienteUpdateDados obj2 = new ClienteUpdateDados(cliente_logado);
				obj2.setVisible(true);

			}
		});
		mntmAtualizarInformaes.setForeground(Color.DARK_GRAY);
		mntmAtualizarInformaes.setFont(new Font("Arial", Font.BOLD, 14));
		mnPerfil.add(mntmAtualizarInformaes);

		JButton btnNewButton = new JButton("DESLOGAR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ClienteMenu obj = new ClienteMenu(cliente_logado);
				obj.setVisible(false);
				dispose();

			}
		});
		// BOTAO DE SAIR
		btnNewButton.setIcon(new ImageIcon(ClienteMenu.class.getResource("/imagens/status_busy.png")));
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 14));
		btnNewButton.setBackground(new Color(255, 255, 255));
		menuBar.add(btnNewButton);

		contentPane = new JPanel();
		contentPane.setForeground(Color.DARK_GRAY);
		contentPane.setBackground(new Color(0, 0, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		JLabel lblNewLabel = new JLabel("BEM VINDO A STEAM");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setBounds(167, 25, 624, 75);
		contentPane.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 55));

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(ClienteMenu.class.getResource("/imagens/games.png")));
		lblNewLabel_1.setBounds(561, 115, 600, 514);
		contentPane.add(lblNewLabel_1);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ClienteMenu.class.getResource("/imagens/games.png")));
		label.setBounds(-14, 115, 600, 514);
		contentPane.add(label);

	}
}
