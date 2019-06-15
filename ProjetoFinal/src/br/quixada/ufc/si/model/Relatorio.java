package br.quixada.ufc.si.model;

import java.sql.Timestamp;

public class Relatorio {
	private String cpfCliente;
	private String nomeJogo;
	private Timestamp dataCompra;

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public String getNomeJogo() {
		return nomeJogo;
	}

	public void setNomeJogo(String nomeJogo) {
		this.nomeJogo = nomeJogo;
	}

	public Timestamp getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(Timestamp dataCompra) {
		this.dataCompra = dataCompra;
	}
}
