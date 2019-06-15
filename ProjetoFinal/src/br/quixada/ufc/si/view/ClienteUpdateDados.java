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

public class ClienteUpdateDados extends JFrame {

	private JPanel contentPane;
	private JTextField textnome;
	private JTextField textlogin;
	private JTextField textsenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cliente cliente = new Cliente();
					ClienteUpdateDados frame = new ClienteUpdateDados(cliente);
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
	public ClienteUpdateDados(Cliente cliente_logado) {
		setResizable(false);
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(ClienteUpdateDados.class.getResource("/imagens/Steam-icon.png")));
		setTitle("Atualizar Dados");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 658, 408);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		this.setLocationRelativeTo(null);
		contentPane.setLayout(null);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNome.setForeground(Color.WHITE);
		lblNome.setBounds(52, 92, 41, 16);
		contentPane.add(lblNome);

		textnome = new JTextField();
		textnome.setForeground(Color.BLACK);
		textnome.setFont(new Font("Arial", Font.PLAIN, 16));
		textnome.setColumns(10);
		textnome.setBackground(Color.WHITE);
		textnome.setBounds(142, 82, 401, 36);
		contentPane.add(textnome);

		textlogin = new JTextField();
		textlogin.setForeground(Color.BLACK);
		textlogin.setFont(new Font("Arial", Font.PLAIN, 16));
		textlogin.setColumns(10);
		textlogin.setBackground(Color.WHITE);
		textlogin.setBounds(142, 130, 401, 36);
		contentPane.add(textlogin);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setForeground(Color.WHITE);
		lblLogin.setFont(new Font("Arial", Font.PLAIN, 14));
		lblLogin.setBounds(52, 140, 56, 16);
		contentPane.add(lblLogin);

		textsenha = new JTextField();
		textsenha.setForeground(Color.BLACK);
		textsenha.setFont(new Font("Arial", Font.PLAIN, 16));
		textsenha.setColumns(10);
		textsenha.setBackground(Color.WHITE);
		textsenha.setBounds(142, 179, 401, 36);
		contentPane.add(textsenha);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Arial", Font.PLAIN, 14));
		lblSenha.setForeground(Color.WHITE);
		lblSenha.setBounds(46, 189, 47, 16);
		contentPane.add(lblSenha);

		JButton btnAtualizar = new JButton("ATUALIZAR");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if ((textnome.getText().isEmpty()) || (textlogin.getText().isEmpty())
						|| (textsenha.getText().isEmpty())) { // VERIFICA SE OS CAMPOS ESTAO VAZIOS
					JOptionPane.showMessageDialog(null, "Os campos não podem estar vazios");
				} else {
					ClienteDAO dao = new ClienteDAO(br.quixada.ufc.si.view.viewLogin.con);

					cliente_logado.setNome(textnome.getText());
					cliente_logado.setLogin(textlogin.getText());
					cliente_logado.setSenha(textsenha.getText());

					try {
						dao.upCliente(cliente_logado);
						JOptionPane.showMessageDialog(null,
								"Cliente " + textnome.getText() + " atualizado com sucesso! ");
					} catch (NoSuchAlgorithmException | UnsupportedEncodingException e1) {
						e1.printStackTrace();
					}
				}

				// APAGA OS DADOS DOS CAMPOS APOS CADASTRAR
				textnome.setText("");
				textlogin.setText("");
				textsenha.setText("");

			}
		});
		btnAtualizar.setForeground(Color.WHITE);
		btnAtualizar.setBackground(new Color(51, 51, 51));
		btnAtualizar.setBounds(154, 276, 117, 25);
		contentPane.add(btnAtualizar);

		JButton btnReiniciar = new JButton("VOLTAR");
		btnReiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ClienteUpdateDados obj1 = new ClienteUpdateDados(cliente_logado);
				obj1.setVisible(false);
				dispose();

				/*
				 * MenuPrincipal obj2 = new MenuPrincipal(cliente_logado);
				 * obj2.setVisible(false); dispose();
				 */
			}
		});
		btnReiniciar.setForeground(Color.WHITE);
		btnReiniciar.setBackground(new Color(51, 51, 51));
		btnReiniciar.setBounds(353, 276, 117, 25);
		contentPane.add(btnReiniciar);
	}
}
