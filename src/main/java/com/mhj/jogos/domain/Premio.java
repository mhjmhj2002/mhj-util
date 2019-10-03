package com.mhj.jogos.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Premio {

	private Long id;
	private Integer quantidadeAcertos;
	private BigDecimal valor;
	private BigDecimal valorAcumulado;

	private Concurso concurso;

	private List<Ganhador> ganhadores;

	public Premio() {
		ganhadores = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantidadeAcertos() {
		return quantidadeAcertos;
	}

	public void setQuantidadeAcertos(Integer quantidadeAcertos) {
		this.quantidadeAcertos = quantidadeAcertos;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Concurso getConcurso() {
		return concurso;
	}

	public void setConcurso(Concurso concurso) {
		this.concurso = concurso;
	}

	public List<Ganhador> getGanhadores() {
		return ganhadores;
	}

	public void setGanhadores(List<Ganhador> ganhadores) {
		this.ganhadores = ganhadores;
	}

	public BigDecimal getValorAcumulado() {
		return valorAcumulado;
	}

	public void setValorAcumulado(BigDecimal valorAcumulado) {
		this.valorAcumulado = valorAcumulado;
	}

}
