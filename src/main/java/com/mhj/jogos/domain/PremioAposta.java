package com.mhj.jogos.domain;

import java.util.ArrayList;
import java.util.List;

public class PremioAposta {

	private Integer acertos;
	private List<Concurso> concursos;

	public PremioAposta() {
		concursos = new ArrayList<Concurso>();
	}

	public Integer getAcertos() {
		return acertos;
	}

	public void setAcertos(Integer acertos) {
		this.acertos = acertos;
	}

	public List<Concurso> getConcursos() {
		return concursos;
	}

	public void setConcursos(List<Concurso> concursos) {
		this.concursos = concursos;
	}

}
