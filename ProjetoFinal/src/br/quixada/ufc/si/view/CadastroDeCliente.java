package br.quixada.ufc.si.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.quixada.ufc.si.dao.ClienteDAO;
import br.quixada.ufc.si.model.Cliente;

public class CadastroDeCliente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNome;
	private JTextField textCPF;
	private JTextField textUsuario;
	private JTextField textSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroDeCliente frame = new CadastroDeCliente();
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
	public CadastroDeCliente() {
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(CadastroDeCliente.class.getResource("/imagens/Steam-icon.png")));
		setTitle("Cadastro de Usuario");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 660, 419);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 51, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);

		JLabel lblInsiraSeuNome = new JLabel("Insira seu nome");
		lblInsiraSeuNome.setFont(new Font("Arial", Font.PLAIN, 14));
		lblInsiraSeuNome.setForeground(new Color(255, 255, 255));
		lblInsiraSeuNome.setBounds(46, 38, 130, 15);
		contentPane.add(lblInsiraSeuNome);

		textNome = new JTextField();
		textNome.setFont(new Font("Arial", Font.PLAIN, 16));
		textNome.setForeground(Color.WHITE);
		textNome.setBackground(Color.DARK_GRAY);
		textNome.setBounds(188, 27, 401, 36);
		contentPane.add(textNome);
		textNome.setColumns(10);

		JLabel lblInsiraSeuCpf = new JLabel("Insira seu CPF");
		lblInsiraSeuCpf.setFont(new Font("Arial", Font.PLAIN, 14));
		lblInsiraSeuCpf.setForeground(new Color(255, 255, 255));
		lblInsiraSeuCpf.setBounds(51, 91, 119, 15);
		contentPane.add(lblInsiraSeuCpf);

		textCPF = new JTextField();
		try {
			javax.swing.text.MaskFormatter fomatoCpf = new javax.swing.text.MaskFormatter("###########");
			textCPF = new javax.swing.JFormattedTextField(fomatoCpf);
		} catch (Exception e) {
			e.printStackTrace();
		}
		textCPF.setFont(new Font("Arial", Font.PLAIN, 16));
		textCPF.setBackground(Color.DARK_GRAY);
		textCPF.setForeground(new Color(255, 255, 255));
		textCPF.setBounds(188, 81, 401, 36);
		contentPane.add(textCPF);
		textCPF.setColumns(10);

		JLabel lblInsiraUmNome = new JLabel("Nome de usuario");
		lblInsiraUmNome.setFont(new Font("Arial", Font.PLAIN, 14));
		lblInsiraUmNome.setForeground(new Color(255, 255, 255));
		lblInsiraUmNome.setBounds(35, 145, 135, 15);
		contentPane.add(lblInsiraUmNome);

		textUsuario = new JTextField();
		textUsuario.setFont(new Font("Arial", Font.PLAIN, 16));
		textUsuario.setForeground(new Color(255, 255, 255));
		textUsuario.setBackground(Color.DARK_GRAY);
		textUsuario.setBounds(188, 135, 401, 36);
		contentPane.add(textUsuario);
		textUsuario.setColumns(10);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Arial", Font.PLAIN, 14));
		lblSenha.setForeground(new Color(255, 255, 255));
		lblSenha.setBounds(100, 201, 70, 15);
		contentPane.add(lblSenha);

		JButton btnCadastrar = new JButton("CADASTRAR");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Cliente cliente = new Cliente();

				if (textCPF.getText().matches("\\d+") && (textCPF.getText().length() == 11)
						&& (!textNome.getText().matches("\\d+"))) {

					cliente.setNome(textNome.getText());
					cliente.setCpf(textCPF.getText());
					cliente.setLogin(textUsuario.getText());
					cliente.setSenha(textSenha.getText());

					if ((textNome.getText().isEmpty()) || (textCPF.getText().isEmpty())
							|| (textUsuario.getText().isEmpty()) || (textSenha.getText().isEmpty())) { // VERIFICA SE OS
																										// CAMPOS ESTAO
																										// VAZIOS
						JOptionPane.showMessageDialog(null, "Os campos não podem estar vazios");
					} else {
						ClienteDAO dao = new ClienteDAO(br.quixada.ufc.si.view.viewLogin.con);

						try {
							dao.addCliente(cliente);
							JOptionPane.showMessageDialog(null,
									"Cliente " + textNome.getText() + " inserido com sucesso! ");
						} catch (NoSuchAlgorithmException | UnsupportedEncodingException e1) {
							e1.printStackTrace();
						}
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Os campos Nome não pode conter números e o CPF não pode conter letras");
				}

				// APAGA OS DADOS DOS CAMPOS APOS CADASTRAR
				textNome.setText("");
				textCPF.setText("");
				textUsuario.setText("");
				textSenha.setText("");
			}
		});

		textSenha = new JTextField();
		textSenha.setForeground(Color.WHITE);
		textSenha.setFont(new Font("Arial", Font.PLAIN, 16));
		textSenha.setColumns(10);
		textSenha.setBackground(Color.DARK_GRAY);
		textSenha.setBounds(188, 197, 401, 36);
		contentPane.add(textSenha);
		btnCadastrar.setForeground(new Color(255, 255, 255));
		btnCadastrar.setBackground(new Color(51, 51, 51));
		btnCadastrar.setBounds(174, 269, 117, 25);
		contentPane.add(btnCadastrar);

		JButton btnVoltar = new JButton("VOLTAR");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CadastroDeCliente obj = new CadastroDeCliente();
				obj.setVisible(false);
				dispose();

			}
		});
		btnVoltar.setBackground(new Color(51, 51, 51));
		btnVoltar.setForeground(new Color(255, 255, 255));
		btnVoltar.setBounds(369, 269, 117, 25);
		contentPane.add(btnVoltar);
	}

	protected static void DISPOSE_ON_CLOSE() {
		// TODO Auto-generated method stub

	}
}
