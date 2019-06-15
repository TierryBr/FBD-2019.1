package br.quixada.ufc.si.model;

public class Administrador {
	private String login;
	private String senha;
	private String nome;
	private String cpf;

	public Administrador() {
		super();
	}

	public Administrador(String nome, String cpf, String login, String senha) {
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

	public void editarDados(String nome, String CPF, String login, String senha) {
		this.setNome(nome);
		this.setCpf(CPF);
		this.setLogin(login);
		this.setSenha(senha);
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
