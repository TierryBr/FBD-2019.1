package br.quixada.ufc.si.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.quixada.ufc.si.dao.AdministradorDAO;
import br.quixada.ufc.si.model.Jogos;

public class AdmCadastroJogos extends JFrame {

	private JPanel contentPane;
	private JTextField textnome;
	private JTextField texttipo;
	private JTextField textpreco;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdmCadastroJogos frame = new AdmCadastroJogos();
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
	public AdmCadastroJogos() {
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(AdmCadastroJogos.class.getResource("/imagens/Steam-icon.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 672, 447);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 51, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNome.setForeground(new Color(255, 255, 255));
		lblNome.setBounds(82, 69, 37, 16);
		contentPane.add(lblNome);

		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setForeground(new Color(255, 255, 255));
		lblTipo.setFont(new Font("Arial", Font.PLAIN, 14));
		lblTipo.setBounds(91, 146, 28, 16);
		contentPane.add(lblTipo);

		JLabel lblPreo = new JLabel("Pre\u00E7o");
		lblPreo.setFont(new Font("Arial", Font.PLAIN, 14));
		lblPreo.setForeground(new Color(255, 255, 255));
		lblPreo.setBounds(82, 224, 37, 16);
		contentPane.add(lblPreo);

		textnome = new JTextField();
		textnome.setForeground(Color.WHITE);
		textnome.setFont(new Font("Arial", Font.PLAIN, 16));
		textnome.setColumns(10);
		textnome.setBackground(Color.DARK_GRAY);
		textnome.setBounds(168, 54, 401, 36);
		contentPane.add(textnome);

		texttipo = new JTextField();
		texttipo.setForeground(Color.WHITE);
		texttipo.setFont(new Font("Arial", Font.PLAIN, 16));
		texttipo.setColumns(10);
		texttipo.setBackground(Color.DARK_GRAY);
		texttipo.setBounds(168, 132, 401, 36);
		contentPane.add(texttipo);

		textpreco = new JTextField();
		textpreco.setForeground(Color.WHITE);
		textpreco.setFont(new Font("Arial", Font.PLAIN, 16));
		textpreco.setColumns(10);
		textpreco.setBackground(Color.DARK_GRAY);
		textpreco.setBounds(168, 208, 401, 36);
		contentPane.add(textpreco);

		JButton btnCadastrar = new JButton("CADASTRAR");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Jogos jogo = new Jogos();

				if (textpreco.getText().matches("\\d+")) {

					jogo.setNome(textnome.getText());
					jogo.setTipo(texttipo.getText());
					jogo.setPreco(Double.parseDouble(textpreco.getText()));

					if ((textnome.getText().isEmpty()) || (texttipo.getText().isEmpty())
							|| (textpreco.getText().isEmpty())) { // VERIFICA SE OS CAMPOS ESTAO VAZIOS
						JOptionPane.showMessageDialog(null, "Os campos não podem estar vazios");
					} else {
						AdministradorDAO dao = new AdministradorDAO(br.quixada.ufc.si.view.viewLogin.con);
						dao.addJogo(jogo);
						JOptionPane.showMessageDialog(null, "Jogo " + textnome.getText() + " inserido com sucesso! ");
					}

				} else {
					JOptionPane.showMessageDialog(null, "O campo Preço não aceita letras!");
				}

				// APAGA OS DADOS DOS CAMPOS APOS CADASTRAR
				textnome.setText("");
				texttipo.setText("");
				textpreco.setText("");

			}
		});
		btnCadastrar.setForeground(Color.WHITE);
		btnCadastrar.setBackground(new Color(51, 51, 51));
		btnCadastrar.setBounds(168, 311, 117, 25);
		contentPane.add(btnCadastrar);

		JButton button = new JButton("VOLTAR");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				AdmCadastroJogos obj = new AdmCadastroJogos();
				obj.setVisible(false);
				dispose();

			}
		});
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(51, 51, 51));
		button.setBounds(369, 311, 117, 25);
		contentPane.add(button);
	}
}
