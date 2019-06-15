package br.quixada.ufc.si.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.quixada.ufc.si.conexao.conexaoJDBC;
import br.quixada.ufc.si.model.Jogos;

public class JogosDAO {

	private Connection connection;
	private conexaoJDBC conexaoJDBC;

	public JogosDAO(conexaoJDBC conexaoJDBC) {
		this.conexaoJDBC = conexaoJDBC;
	}

	public ArrayList<Jogos> getListaJogos(int escolha) {
		if (escolha == 1) {
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
		} else if (escolha == 2) {

			String sql = "SELECT * FROM listarTipos;";
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

		} else if (escolha == 3) {

			String sql = "SELECT * FROM listaMenorPreco;";
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

		} else {

			String sql = "SELECT * FROM listaMaiorPreco;";
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

		}

		return null;

	}

	public Jogos procurarJogo(String nome_jogo) {
		String sql = "SELECT * FROM jogos where nome = ?;";
		Jogos Jogo = null;
		this.connection = conexaoJDBC.getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, nome_jogo);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String nome = rs.getString("nome");
				String tipo = rs.getString("tipo");
				double preco = rs.getDouble("preco");

				Jogo = new Jogos(nome, tipo, preco);

			}
			stmt.close();

			return Jogo;

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
