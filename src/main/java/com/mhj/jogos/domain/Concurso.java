package com.mhj.jogos.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Concurso {

	private Long id;
	private Long numero;
	private Date data;
	private BigDecimal arrecadacao;
	private Jogo jogo;
	private List<Dezena> dezenas;
	private List<Premio> premios;

	public Concurso() {
		dezenas = new ArrayList<>();
		premios = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public BigDecimal getArrecadacao() {
		return arrecadacao;
	}

	public void setArrecadacao(BigDecimal arrecadacao) {
		this.arrecadacao = arrecadacao;
	}

	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}

	public List<Dezena> getDezenas() {
		return dezenas;
	}

	public void setDezenas(List<Dezena> dezenas) {
		this.dezenas = dezenas;
	}

	public List<Premio> getPremios() {
		return premios;
	}

	public void setPremios(List<Premio> premios) {
		this.premios = premios;
	}
}
