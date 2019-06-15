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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.quixada.ufc.si.dao.ClienteDAO;
import br.quixada.ufc.si.model.Jogos;

public class ClienteListarJogos extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClienteListarJogos frame = new ClienteListarJogos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ClienteListarJogos() {

	}

	/**
	 * Create the frame.
	 */
	public ClienteListarJogos(String cpf) {
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(ClienteListarJogos.class.getResource("/imagens/Steam-icon.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 956, 702);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 926, 568);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Arial", Font.BOLD, 14));
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null }, },
				new String[] { "Tipo", "Nome", "Pre\u00E7o" }));
		scrollPane.setViewportView(table);

		button = new JButton("VOLTAR");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ClienteListarJogos obj = new ClienteListarJogos();
				obj.setVisible(false);
				dispose();

			}
		});
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(51, 51, 51));
		button.setBounds(422, 609, 117, 25);
		contentPane.add(button);

		ClienteDAO cliente = new ClienteDAO(br.quixada.ufc.si.view.viewLogin.con);

		ArrayList<Jogos> listaJogos = cliente.getListaJogos(cpf);

		// SETANDO PARA A TABELA COMEÇAR COM NENHUMA LINHA
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.setNumRows(0);

		for (int i = 0; i < listaJogos.size(); i++) {
			modelo.addRow(new Object[] { listaJogos.get(i).getNome(), listaJogos.get(i).getTipo(),
					listaJogos.get(i).getPreco() });
		}

	}

}
