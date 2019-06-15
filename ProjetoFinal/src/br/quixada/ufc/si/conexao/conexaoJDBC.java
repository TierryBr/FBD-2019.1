package br.quixada.ufc.si.conexao;

import java.sql.Connection;
import java.sql.DriverManager;

public class conexaoJDBC {

	static String URL = "jdbc:postgresql://localhost:5432/Steam";
	static String USER = "postgres";
	static String PASS = "postgres";
	static Connection conecta;

	public static Connection getConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			conecta = DriverManager.getConnection(URL, USER, PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conecta;
	}
}
