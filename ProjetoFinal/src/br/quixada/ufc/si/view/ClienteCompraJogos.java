package br.quixada.ufc.si.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.quixada.ufc.si.dao.ClienteDAO;
import br.quixada.ufc.si.dao.JogosDAO;
import br.quixada.ufc.si.model.Cliente;
import br.quixada.ufc.si.model.Jogos;

public class ClienteCompraJogos extends JFrame {

	private JPanel contentPane;
	JRadioButton rdbtnNewRadioButton = new JRadioButton("Tipo");
	JRadioButton rdbtnMenorPreo = new JRadioButton("Menor Pre\u00E7o");
	JRadioButton rdbtnMaiorPreo = new JRadioButton("Maior Pre\u00E7o");
	ButtonGroup bg = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cliente cliente = new Cliente();
					ClienteCompraJogos frame = new ClienteCompraJogos(cliente);
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
	public ClienteCompraJogos(Cliente cliente_logado) {
		setResizable(false);
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(ClienteCompraJogos.class.getResource("/imagens/Steam-icon.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 956, 702);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);

		// SCROL PARA ROLAR PARA BAIXO CASO HAJA MUITOS JOGOS
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 62, 926, 418);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setVisible(true);
		contentPane.add(scrollPane);

		// CHAMA A TABELA DE JOGOS CADASTRADOS
		JTable tabelaJogos = new JTable();
		tabelaJogos.setFont(new Font("Arial", Font.BOLD, 14));
		tabelaJogos.setBackground(Color.WHITE);
		tabelaJogos.setForeground(Color.BLACK);
		tabelaJogos.setModel(new DefaultTableModel(new Object[][] { { null, null, null }, },
				new String[] { "NOME", "TIPO", "PREÇO" }) {

			boolean[] columnEditables = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(tabelaJogos);

		JogosDAO dj = new JogosDAO(br.quixada.ufc.si.view.viewLogin.con);
		ArrayList<Jogos> listaJogos = dj.getListaJogos(1);
		// SETANDO PARA A TABELA COMEÇAR COM NENHUMA LINHA
		DefaultTableModel modelo = (DefaultTableModel) tabelaJogos.getModel();
		modelo.setNumRows(0);

		for (int i = 0; i < listaJogos.size(); i++) {
			modelo.addRow(new Object[] { listaJogos.get(i).getNome(), listaJogos.get(i).getTipo(),
					listaJogos.get(i).getPreco() });
		}

		JLabel lblNewLabel_1 = new JLabel("Digite o nome do jogo que deseja comprar");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(319, 504, 284, 16);
		contentPane.add(lblNewLabel_1);

		JTextField textCompra = new JTextField();
		textCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		textCompra.setForeground(Color.WHITE);
		textCompra.setFont(new Font("Arial", Font.PLAIN, 15));
		textCompra.setColumns(10);
		textCompra.setBackground(Color.DARK_GRAY);
		textCompra.setBounds(257, 533, 401, 36);
		contentPane.add(textCompra);

		JButton btnComprar = new JButton("COMPRAR");
		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Jogos jog = new Jogos();
				jog.setNome(textCompra.getText());

				if (textCompra.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "O campo não pode estar vazio");
				} else {
					ClienteDAO dao = new ClienteDAO(br.quixada.ufc.si.view.viewLogin.con);

					try {
						if (dao.compra(cliente_logado, jog)) {
							JOptionPane.showMessageDialog(null,
									"Jogo " + textCompra.getText() + " comprado com sucesso! ");
						} else {
							JOptionPane.showMessageDialog(null, "Jogo inexistente ou você já possui este jogo!");
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}

				// APAGA OS DADOS DOS CAMPOS APOS CADASTRAR
				textCompra.setText("");
			}
		});
		btnComprar.setForeground(Color.WHITE);
		btnComprar.setBackground(new Color(51, 51, 51));
		btnComprar.setBounds(319, 595, 117, 25);
		contentPane.add(btnComprar);

		JButton button = new JButton("VOLTAR");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ClienteCompraJogos obj = new ClienteCompraJogos(cliente_logado);
				obj.setVisible(false);
				dispose();

			}
		});
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(51, 51, 51));
		button.setBounds(486, 595, 117, 25);
		contentPane.add(button);

		JLabel lblNewLabel = new JLabel("Ordenar por:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(12, 23, 96, 16);
		contentPane.add(lblNewLabel);

		// BOTAO DO TIPO
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				JogosDAO dj = new JogosDAO(br.quixada.ufc.si.view.viewLogin.con);
				ArrayList<Jogos> listaJogos = dj.getListaJogos(2);
				// SETANDO PARA A TABELA COMEÇAR COM NENHUMA LINHA
				DefaultTableModel modelo = (DefaultTableModel) tabelaJogos.getModel();
				modelo.setNumRows(0);

				for (int i = 0; i < listaJogos.size(); i++) {
					modelo.addRow(new Object[] { listaJogos.get(i).getNome(), listaJogos.get(i).getTipo(),
							listaJogos.get(i).getPreco() });
				}

			}
		});
		rdbtnNewRadioButton.setForeground(new Color(255, 255, 255));
		rdbtnNewRadioButton.setFont(new Font("Arial", Font.PLAIN, 14));
		rdbtnNewRadioButton.setBackground(new Color(0, 0, 51));
		rdbtnNewRadioButton.setBounds(120, 20, 96, 25);
		contentPane.add(rdbtnNewRadioButton);

		// BOTAO MENOR PRECO
		rdbtnMenorPreo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JogosDAO dj = new JogosDAO(br.quixada.ufc.si.view.viewLogin.con);
				ArrayList<Jogos> listaJogos = dj.getListaJogos(3);
				// SETANDO PARA A TABELA COMEÇAR COM NENHUMA LINHA
				DefaultTableModel modelo = (DefaultTableModel) tabelaJogos.getModel();
				modelo.setNumRows(0);

				for (int i = 0; i < listaJogos.size(); i++) {
					modelo.addRow(new Object[] { listaJogos.get(i).getNome(), listaJogos.get(i).getTipo(),
							listaJogos.get(i).getPreco() });
				}

			}
		});
		rdbtnMenorPreo.setForeground(Color.WHITE);
		rdbtnMenorPreo.setFont(new Font("Arial", Font.PLAIN, 14));
		rdbtnMenorPreo.setBackground(new Color(0, 0, 51));
		rdbtnMenorPreo.setBounds(220, 20, 141, 25);
		contentPane.add(rdbtnMenorPreo);

		// BOTAO MAIOR PRECO
		rdbtnMaiorPreo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JogosDAO dj = new JogosDAO(br.quixada.ufc.si.view.viewLogin.con);
				ArrayList<Jogos> listaJogos = dj.getListaJogos(4);
				// SETANDO PARA A TABELA COMEÇAR COM NENHUMA LINHA
				DefaultTableModel modelo = (DefaultTableModel) tabelaJogos.getModel();
				modelo.setNumRows(0);

				for (int i = 0; i < listaJogos.size(); i++) {
					modelo.addRow(new Object[] { listaJogos.get(i).getNome(), listaJogos.get(i).getTipo(),
							listaJogos.get(i).getPreco() });
				}

			}
		});
		rdbtnMaiorPreo.setForeground(Color.WHITE);
		rdbtnMaiorPreo.setFont(new Font("Arial", Font.PLAIN, 14));
		rdbtnMaiorPreo.setBackground(new Color(0, 0, 51));
		rdbtnMaiorPreo.setBounds(365, 20, 141, 25);
		contentPane.add(rdbtnMaiorPreo);
		bg.add(rdbtnNewRadioButton);
		bg.add(rdbtnMenorPreo);
		bg.add(rdbtnMaiorPreo);
	}
}
