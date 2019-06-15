package br.quixada.ufc.si.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import br.quixada.ufc.si.conexao.conexaoJDBC;
import br.quixada.ufc.si.dao.AdministradorDAO;
import br.quixada.ufc.si.dao.ClienteDAO;
import br.quixada.ufc.si.model.Administrador;
import br.quixada.ufc.si.model.Cliente;

public class viewLogin extends JFrame implements ActionListener {

	static conexaoJDBC con = new conexaoJDBC();

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTextField textLogin;
	JRadioButton ButtonUsuario = new JRadioButton("Usuario");
	JRadioButton ButtonAdministrador = new JRadioButton("Administrador");
	ButtonGroup bg = new ButtonGroup();
	private boolean verifica = false;
	private JPasswordField senhaField;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public viewLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// TELA DO LOGIN

		frame = new JFrame();
		frame.setIconImage(
				Toolkit.getDefaultToolkit().getImage(viewLogin.class.getResource("/imagens/Steam-icon.png")));
		frame.setResizable(false); // TIRAR OU COLOCAR A OPÃ‡ÃƒO DE MAXIMIZAR
		frame.setTitle("Iniciar sessão no Steam"); // NOME DA TELA
		frame.setBounds(100, 100, 633, 410);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JLabel lblLogin = new JLabel("Nome de usuario");
		lblLogin.setBounds(44, 102, 139, 15);
		lblLogin.setForeground(Color.WHITE);
		lblLogin.setBackground(Color.WHITE);
		frame.getContentPane().add(lblLogin);

		textLogin = new JTextField();
		textLogin.setFont(new Font("Arial", Font.PLAIN, 14));
		textLogin.setForeground(Color.WHITE);
		textLogin.setBackground(Color.GRAY);
		textLogin.setBounds(177, 97, 389, 26);
		frame.getContentPane().add(textLogin);
		textLogin.setColumns(10);

		senhaField = new JPasswordField();
		senhaField.setForeground(Color.WHITE);
		senhaField.setBackground(Color.GRAY);
		senhaField.setFont(new Font("Arial", Font.PLAIN, 14));
		senhaField.setBounds(177, 136, 389, 26);
		frame.getContentPane().add(senhaField);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(105, 142, 60, 15);
		lblSenha.setForeground(Color.WHITE);
		frame.getContentPane().add(lblSenha);

		JButton BotaoLogar = new JButton("INICIAR SESSÃO");
		BotaoLogar.setBackground(new Color(51, 51, 51));
		BotaoLogar.setForeground(new Color(240, 255, 255));
		BotaoLogar.setBounds(417, 191, 149, 25);
		BotaoLogar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (verifica == true) {

					Cliente cliente = new Cliente();

					if ((textLogin.getText().isEmpty()) || (new String(senhaField.getPassword()).isEmpty())) { // verifica
																												// se os
																												// campos
						// estao
						// vazios
						JOptionPane.showMessageDialog(null, "Os campos não podem estar vazios");
					} else {

						ClienteDAO dao = new ClienteDAO(con);
						cliente.setLogin(textLogin.getText());

						if (dao.procurarCliente(cliente.getLogin()) != null) {
							cliente = dao.procurarCliente(cliente.getLogin());
						}
						cliente.setSenha(new String(senhaField.getPassword()));

						try {
							if (dao.login(cliente)) {
								ClienteMenu obj2 = new ClienteMenu(cliente);
								obj2.setVisible(true);
							} else {
								JOptionPane.showMessageDialog(null, "ERRO! Login ou Senha não existe\n");
							}
						} catch (NoSuchAlgorithmException | UnsupportedEncodingException e1) {
							e1.printStackTrace();
						}
					}

					// apaga os dados dos campos apos cadastrar
					textLogin.setText("");
					senhaField.setText("");
				} else {

					Administrador adm = new Administrador();

					if ((textLogin.getText().isEmpty()) || (new String(senhaField.getPassword()).isEmpty())) { // verifica
																												// se os
																												// campos
						// estao
						// vazios
						JOptionPane.showMessageDialog(null, "Os campos não podem estar vazios");
					} else {
						adm.setLogin(textLogin.getText());
						adm.setSenha(new String(senhaField.getPassword()));
						AdministradorDAO dao = new AdministradorDAO(con);

						try {

							if (dao.login(adm)) {
								AdmMenu obj2 = new AdmMenu();
								obj2.setVisible(true);
							} else {
								JOptionPane.showMessageDialog(null, "ERRO! Login ou Senha não existe\n");
							}

						} catch (NoSuchAlgorithmException | UnsupportedEncodingException e1) {
							e1.printStackTrace();
						}

					}
					// apaga os dados dos campos apos cadastrar
					textLogin.setText("");
					senhaField.setText("");
				}

			}
		});

		// BUTTON DO USUARIO
		ButtonUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				verifica = true;

			}
		});

		frame.getContentPane().add(BotaoLogar);
		ButtonUsuario.setForeground(Color.WHITE);
		ButtonUsuario.setBackground(new Color(0, 0, 0));
		ButtonUsuario.setBounds(139, 192, 93, 23);
		frame.getContentPane().add(ButtonUsuario);

		// BUTTON DO ADMINISTRADOR
		ButtonAdministrador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				verifica = false;

			}
		});
		ButtonAdministrador.setForeground(Color.WHITE);
		ButtonAdministrador.setBackground(new Color(0, 0, 0));
		ButtonAdministrador.setBounds(234, 192, 149, 23);
		frame.getContentPane().add(ButtonAdministrador);

		// ADCIONANDO OS DOIS BOTÃ•ES NO BUTTONGRUP
		bg.add(ButtonUsuario);
		bg.add(ButtonAdministrador);

		// ADCIONANDO UMA IMAGEM AO FUNDO DA TELA DE LOGIN
		ImageIcon imagem = new ImageIcon(viewLogin.class.getResource("/imagens/background.jpg"));
		JLabel FundoAzul = new JLabel("");
		FundoAzul.setBounds(0, -11, 723, 399);
		Image imag = imagem.getImage().getScaledInstance(FundoAzul.getWidth(), FundoAzul.getHeight(),
				Image.SCALE_SMOOTH);
		FundoAzul.setIcon(new ImageIcon(imag));

		// ADCIONANDO LOGO DA STEAM
		ImageIcon imagem2 = new ImageIcon(viewLogin.class.getResource("/imagens/steam-logo-1.png"));
		JLabel LogoSteam = new JLabel("");
		LogoSteam.setBounds(65, 12, 236, 71);
		Image imag2 = imagem2.getImage().getScaledInstance(LogoSteam.getWidth(), LogoSteam.getHeight(),
				Image.SCALE_SMOOTH);

		JSeparator separator = new JSeparator();
		separator.setToolTipText("");
		separator.setBounds(45, 248, 537, 15);
		frame.getContentPane().add(separator);

		JLabel lblNaoPossuiSenha = new JLabel("Não possui uma conta Steam ? ");
		lblNaoPossuiSenha.setForeground(Color.WHITE);
		lblNaoPossuiSenha.setBounds(116, 270, 236, 15);
		frame.getContentPane().add(lblNaoPossuiSenha);

		JButton btnNewButton = new JButton("CRIAR UMA NOVA CONTA");
		btnNewButton.addActionListener(this);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CadastroDeCliente obj = new CadastroDeCliente();
				obj.setVisible(true);

			}
		});
		btnNewButton.setBackground(new Color(51, 51, 51));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBounds(363, 265, 203, 25);
		frame.getContentPane().add(btnNewButton);

		JLabel lblEstaComDuvidas = new JLabel("Esta com duvidas ?");
		lblEstaComDuvidas.setForeground(Color.WHITE);
		lblEstaComDuvidas.setBounds(177, 307, 114, 15);
		frame.getContentPane().add(lblEstaComDuvidas);

		JButton btnInformacoes = new JButton("INFORMAÇÕES");
		btnInformacoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"       FBD 2019.1 \n\n    Desenvolvedores:\n             Tierry \n          Jhonatan \n",
						"Informações", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnInformacoes.setForeground(Color.WHITE);
		btnInformacoes.setBackground(new Color(51, 51, 51));
		btnInformacoes.setBounds(363, 302, 203, 25);
		frame.getContentPane().add(btnInformacoes);
		LogoSteam.setIcon(new ImageIcon(imag2));

		// ADCIONANDO AS IMAGENS AO PAINEL
		frame.getContentPane().add(LogoSteam);
		frame.getContentPane().add(FundoAzul);

	}

	public static void main(String[] args) {
		new Loading();

		// USANDO TEMA DO NIMBUS NA APLICAÃ‡ÃƒO
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			System.err.println(ex);
		} catch (InstantiationException ex) {
			System.err.println(ex);
		} catch (IllegalAccessException ex) {
			System.err.println(ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			System.err.println(ex);
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					viewLogin window = new viewLogin();
					window.frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
