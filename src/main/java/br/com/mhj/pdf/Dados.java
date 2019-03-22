package br.com.mhj.pdf;

import java.util.ArrayList;
import java.util.List;

public class Dados {
	private List<Dado> dados;

	public Dados() {
		super();
		dados = new ArrayList<Dado>();
	}
	
	public void addDado(Dado dado) {
		dados.add(dado);
	}

	public List<Dado> getDados() {
		return dados;
	}	
}
