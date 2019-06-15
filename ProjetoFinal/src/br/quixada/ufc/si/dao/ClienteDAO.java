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
import br.quixada.ufc.si.model.Cliente;
import br.quixada.ufc.si.model.Jogos;

public class ClienteDAO {

	private Connection connection;
	private conexaoJDBC conexaoJDBC;

	public ClienteDAO(conexaoJDBC conexaoJDBC) {
		this.conexaoJDBC = conexaoJDBC;
	}

	// CADASTRA CLIENTES NO BANCO
	public boolean addCliente(Cliente cliente) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String sql = "INSERT INTO cliente(nome,cpf,login,senha) VALUES(?,?,?,?)";
		this.connection = conexaoJDBC.getConnection();
		try {

			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getCpf());
			stmt.setString(3, cliente.getLogin());
			stmt.setString(4, script(cliente.getSenha()));

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

	public boolean upCliente(Cliente cliente) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String sql = "UPDATE cliente SET nome = ?, login = ?, senha = ? WHERE cpf = ?";

		try {
			this.connection = conexaoJDBC.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getLogin());
			stmt.setString(3, script(cliente.getSenha()));
			stmt.setString(4, cliente.getCpf());

			int qtdRowsAffected = stmt.executeUpdate();
			stmt.close();
			if (qtdRowsAffected > 0)
				return true;
			return false;
		} catch (SQLException e) {
			System.out.println("");
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean compra(Cliente cliente, Jogos jogos) {
		String sql = "INSERT INTO compras (cpf, nome_jogo) VALUES(?,?);";
		this.connection = conexaoJDBC.getConnection();
		try {

			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, cliente.getCpf());
			stmt.setString(2, jogos.getNome());

			int qtdRowsAffected = stmt.executeUpdate();
			stmt.close();

			if (qtdRowsAffected > 0)
				return true;
			return false;

		} catch (SQLException e) {
			System.out.println("Erro ao comprar!");
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public Cliente procurarCliente(String loginn) {
		String sql = "SELECT * FROM cliente where login = ?;";
		Cliente cliente = null;
		this.connection = conexaoJDBC.getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, loginn);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String nome = rs.getString("nome");
				String cpf = rs.getString("cpf");
				String login = rs.getString("login");
				String senha = rs.getString("senha");
				cliente = new Cliente(nome, cpf, login, senha);

			}
			stmt.close();

			return cliente;

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

	public String script(String a) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest cripto = MessageDigest.getInstance("MD5");
		byte hashing[] = cripto.digest(a.getBytes("UTF-8"));
		StringBuilder hexString = new StringBuilder();
		for (byte b : hashing) {
			hexString.append(String.format("%02X", 0xFF & b));
		}
		return hexString.toString();
	}

	// FAZ O LOGIN DO CLIENTE CADASTRADO
	public boolean login(Cliente cliente) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String sql = "SELECT * FROM cliente WHERE login = ? and senha = ?";
		this.connection = conexaoJDBC.getConnection();
		try {
			ResultSet rs = null;
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, cliente.getLogin());
			stmt.setString(2, script(cliente.getSenha()));
			rs = stmt.executeQuery();

			if (rs.next()) {
				System.out.println("Entrando no Menu...\n");
				return true;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public ArrayList<Jogos> getListaJogos(String cpf_cliente) {
		String sql = "SELECT j.nome, j.tipo, j.preco FROM jogos j right join compras c on j.nome = c.nome_jogo where c.cpf = ?;";
		ArrayList<Jogos> listaJogos = new ArrayList<Jogos>();
		this.connection = conexaoJDBC.getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, cpf_cliente);
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

}
