package br.quixada.ufc.si.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
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
import br.quixada.ufc.si.model.Cliente;

public class AdmRemoverCliente extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textCPF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdmRemoverCliente frame = new AdmRemoverCliente();
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
	public AdmRemoverCliente() {
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
				new String[] { "NOME", "CPF", "LOGIN" }));
		scrollPane.setViewportView(table);

		AdministradorDAO dj = new AdministradorDAO(br.quixada.ufc.si.view.viewLogin.con);
		ArrayList<Cliente> listaCliente = dj.getListaCliente();
		// SETANDO PARA A TABELA COMEÇAR COM NENHUMA LINHA
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.setNumRows(0);

		for (int i = 0; i < listaCliente.size(); i++) {
			modelo.addRow(new Object[] { listaCliente.get(i).getNome(), listaCliente.get(i).getCpf(),
					listaCliente.get(i).getLogin() });
		}

		JLabel lblNewLabel = new JLabel("Digite o CPF do cliente");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel.setBounds(325, 395, 200, 16);
		contentPane.add(lblNewLabel);

		textCPF = new JTextField();
		try {
			javax.swing.text.MaskFormatter fomatoCpf = new javax.swing.text.MaskFormatter("###########");
			textCPF = new javax.swing.JFormattedTextField(fomatoCpf);
		} catch (Exception e) {
			e.printStackTrace();
		}
		textCPF.setForeground(Color.WHITE);
		textCPF.setFont(new Font("Arial", Font.PLAIN, 16));
		textCPF.setColumns(10);
		textCPF.setBackground(Color.DARK_GRAY);
		textCPF.setBounds(209, 424, 401, 36);
		contentPane.add(textCPF);

		JButton btnRemover = new JButton("REMOVER");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String clien = textCPF.getText();

				AdministradorDAO dao = new AdministradorDAO(br.quixada.ufc.si.view.viewLogin.con);
				if (dao.removeCliente(clien)) {
					JOptionPane.showMessageDialog(null,
							"Cliente do CPF: " + textCPF.getText() + " removido com sucesso! ");
				} else {
					JOptionPane.showMessageDialog(null, "Cliente inexistente!");
				}

				// APAGA OS DADOS DOS CAMPOS APOS CADASTRAR
				textCPF.setText("");

			}
		});
		btnRemover.setForeground(Color.WHITE);
		btnRemover.setBackground(new Color(51, 51, 51));
		btnRemover.setBounds(261, 487, 117, 25);
		contentPane.add(btnRemover);

		JButton button = new JButton("VOLTAR");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				AdmRemoverCliente obj = new AdmRemoverCliente();
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
