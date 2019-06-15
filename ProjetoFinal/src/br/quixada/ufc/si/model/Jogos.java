package br.quixada.ufc.si.model;

public class Jogos {
	private String nome;
	private String tipo;
	private double preco;

	public Jogos() {

	}

	public Jogos(String nome, String tipo, double preco) {
		this.nome = nome;
		this.tipo = tipo;
		this.preco = preco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public void editarDados(String nome, String tipo, double preco) {
		this.setNome(nome);
		this.setTipo(tipo);
		this.setPreco(preco);
		System.out.println("Nome atualizado!");
		System.out.println("Tipo atualizada!");
		System.out.println("Preco atualizada!");

	}

	public String toString() {
		return "Nome: " + this.nome + "\n" + "Tipo: " + this.tipo + "\n" + "Preço: " + this.preco + "\n";
	}

}
