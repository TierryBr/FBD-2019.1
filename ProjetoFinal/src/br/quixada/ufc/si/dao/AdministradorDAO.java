package br.quixada.ufc.si.dao;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.quixada.ufc.si.conexao.conexaoJDBC;
import br.quixada.ufc.si.model.Administrador;
import br.quixada.ufc.si.model.Cliente;
import br.quixada.ufc.si.model.Jogos;
import br.quixada.ufc.si.model.Relatorio;

public class AdministradorDAO {

	private Connection connection;
	private conexaoJDBC conexaoJDBC;

	public AdministradorDAO(conexaoJDBC C) {
		this.conexaoJDBC = C;
	}

	public String script(String a) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest cripto = MessageDigest.getInstance("MD5");
		byte hashing[] = cripto.digest(a.getBytes("UTF-8"));
		StringBuilder hexString = new StringBuilder();
		for (byte b : hashing) {
			hexString.append(String.format("%02X", 0xFF & b));
		}
		return hexString.toString();
	}

	public boolean login(Administrador adm) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String sql = "SELECT * FROM administrador WHERE login = ? and senha = ?";
		this.connection = conexaoJDBC.getConnection();
		try {
			ResultSet rs = null;
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, adm.getLogin());
			stmt.setString(2, script(adm.getSenha()));
			rs = stmt.executeQuery();

			if (rs.next()) {
				return true;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public boolean addJogo(Jogos jogo) {
		String sql = "INSERT INTO jogos(nome,tipo,preco) VALUES(?,?,?);";
		this.connection = conexaoJDBC.getConnection();
		try {

			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, jogo.getNome());
			stmt.setString(2, jogo.getTipo());
			stmt.setDouble(3, jogo.getPreco());

			int qtdRowsAffected = stmt.executeUpdate();
			stmt.close();

			if (qtdRowsAffected > 0)
				return true;
			return false;

		} catch (SQLException e) {
			System.out.println("Erro ao inserir!");
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean removeCliente(String cpfRemover) {
		String sql = "Delete from cliente WHERE cpf = ?;";

		try {
			this.connection = conexaoJDBC.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, cpfRemover);

			int qtdRowsAffected = stmt.executeUpdate();
			stmt.close();
			if (qtdRowsAffected > 0)
				return true;
			return false;
		} catch (SQLException e) {
			System.out.println("Erro ao remover\n");
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean removeJogo(String jogoRemover) {
		String sql = "Delete from jogos WHERE nome = ?;";

		try {
			this.connection = conexaoJDBC.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, jogoRemover);

			int qtdRowsAffected = stmt.executeUpdate();
			stmt.close();
			if (qtdRowsAffected > 0)
				return true;
			return false;
		} catch (SQLException e) {
			System.out.println("Erro ao remover\n");
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public ArrayList<Cliente> getListaCliente() {
		String sql = "SELECT * FROM cliente;";
		ArrayList<Cliente> listaJogos = new ArrayList<Cliente>();
		this.connection = conexaoJDBC.getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String nome = rs.getString("nome");
				String cpf = rs.getString("cpf");
				String login = rs.getString("login");
				String senha = rs.getString("senha");
				Cliente cliente = new Cliente(nome, cpf, login, senha);

				listaJogos.add(cliente);
			}

			stmt.close();

			return listaJogos;

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public ArrayList<Jogos> getListaJogos() {
		String sql = "SELECT * FROM jogos;";
		ArrayList<Jogos> listaJogos = new ArrayList<Jogos>();
		this.connection = conexaoJDBC.getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String nome = rs.getString("nome");
				String tipo = rs.getString("tipo");
				double preco = rs.getDouble("preco");

				Jogos jogo = new Jogos(nome, tipo, preco);

				listaJogos.add(jogo);
			}

			stmt.close();

			return listaJogos;

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public ArrayList<Relatorio> relatorioCompras() {
		String sql = "Select * from compras;";
		try {
			this.connection = conexaoJDBC.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			ArrayList<Relatorio> comprasDia = new ArrayList<Relatorio>();

			ResultSet rs = stmt.executeQuery();
			// System.out.println("****Relatorio do dia****\n");
			while (rs.next()) {
				Relatorio unico = new Relatorio();
				unico.setCpfCliente(rs.getString("cpf"));
				unico.setNomeJogo(rs.getString("nome_jogo"));
				unico.setDataCompra(rs.getTimestamp("data_compra"));

				comprasDia.add(unico);
			}
			stmt.close();
			this.connection.close();
			return comprasDia;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
