package br.quixada.ufc.si.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.quixada.ufc.si.dao.AdministradorDAO;
import br.quixada.ufc.si.dao.JogosDAO;
import br.quixada.ufc.si.model.Jogos;

public class AdmRemoverJogos extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textNome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdmRemoverJogos frame = new AdmRemoverJogos();
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
	public AdmRemoverJogos() {
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(AdmRemoverJogos.class.getResource("/imagens/Steam-icon.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 863, 582);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 51, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 833, 351);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Arial", Font.BOLD, 14));
		table.setBackground(Color.WHITE);
		table.setForeground(Color.BLACK);
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null }, },
				new String[] { "NOME", "TIPO", "PREÇO" }));
		scrollPane.setViewportView(table);

		JogosDAO dj = new JogosDAO(br.quixada.ufc.si.view.viewLogin.con);
		ArrayList<Jogos> listaJogos = dj.getListaJogos(1);
		// SETANDO PARA A TABELA COMEÇAR COM NENHUMA LINHA
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.setNumRows(0);

		for (int i = 0; i < listaJogos.size(); i++) {
			modelo.addRow(new Object[] { listaJogos.get(i).getNome(), listaJogos.get(i).getTipo(),
					listaJogos.get(i).getPreco() });
		}

		JLabel lblNewLabel = new JLabel("Digite o nome do jogo");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel.setBounds(347, 395, 145, 16);
		contentPane.add(lblNewLabel);

		textNome = new JTextField();
		textNome.setForeground(Color.WHITE);
		textNome.setFont(new Font("Arial", Font.PLAIN, 16));
		textNome.setColumns(10);
		textNome.setBackground(Color.DARK_GRAY);
		textNome.setBounds(209, 424, 401, 36);
		contentPane.add(textNome);

		JButton btnRemover = new JButton("REMOVER");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String jog = textNome.getText();

				AdministradorDAO dao = new AdministradorDAO(br.quixada.ufc.si.view.viewLogin.con);
				if (dao.removeJogo(jog)) {
					JOptionPane.showMessageDialog(null, "Jogo " + textNome.getText() + " removido com sucesso! ");
				} else {
					JOptionPane.showMessageDialog(null, "Jogo inexistente!");
				}

				// APAGA OS DADOS DOS CAMPOS APOS CADASTRAR
				textNome.setText("");

			}
		});
		btnRemover.setForeground(Color.WHITE);
		btnRemover.setBackground(new Color(51, 51, 51));
		btnRemover.setBounds(261, 487, 117, 25);
		contentPane.add(btnRemover);

		JButton button = new JButton("VOLTAR");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				AdmRemoverJogos obj = new AdmRemoverJogos();
				obj.setVisible(false);
				dispose();

			}
		});
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(51, 51, 51));
		button.setBounds(435, 487, 117, 25);
		contentPane.add(button);
	}

}
