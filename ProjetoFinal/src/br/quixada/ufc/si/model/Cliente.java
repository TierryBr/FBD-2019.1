package br.quixada.ufc.si.model;

import java.util.Map;
import java.util.TreeMap;

public class Cliente {
	private String login;
	private String senha;
	private String nome;
	private String cpf;
	Map<Integer, Jogos> jogos_Comprados = new TreeMap<>();

	public Cliente() {
		super();
	}

	public Cliente(String nome, String cpf, String login, String senha) {
		this.nome = nome;
		this.cpf = cpf;
		this.login = login;
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Map<Integer, Jogos> getjogos_Comprados() {
		return jogos_Comprados;
	}

	public void setJogos_Comprados(Map<Integer, Jogos> jogos_Comprados) {
		this.jogos_Comprados = jogos_Comprados;
	}

	public void editarDados(String nome, String CPF, String login, String senha) {
		setNome(nome);
		setCpf(CPF);
		setLogin(login);
		setSenha(senha);
		System.out.println("Login atualizado!");
		System.out.println("CPF atualizado!");
		System.out.println("Nome atualizado!");
		System.out.println("Senha atualizada!");

	}

	public void mostrarDados() {
		System.out.println("Nome: " + this.nome + "\n" + "Cpf: " + this.cpf + "\n" + "Login: " + this.login + "\n"
				+ "Senha" + this.senha);

	}

}