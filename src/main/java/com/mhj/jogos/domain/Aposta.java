package com.mhj.jogos.domain;

import java.util.ArrayList;
import java.util.List;

public class Aposta {
	private List<Dezena> dezenas;
	PremioAposta premioAposta15;
	PremioAposta premioAposta14;
	PremioAposta premioAposta13;
	PremioAposta premioAposta12;
	PremioAposta premioAposta11;
	
	public Aposta() {
		dezenas = new ArrayList<Dezena>();
		premioAposta15 = new PremioAposta();
		premioAposta14 = new PremioAposta();
		premioAposta13 = new PremioAposta();
		premioAposta12 = new PremioAposta();
		premioAposta11 = new PremioAposta();
	}
	public List<Dezena> getDezenas() {
		return dezenas;
	}
	public void setDezenas(List<Dezena> dezenas) {
		this.dezenas = dezenas;
	}
	public PremioAposta getPremioAposta15() {
		return premioAposta15;
	}
	public void setPremioAposta15(PremioAposta premioAposta15) {
		this.premioAposta15 = premioAposta15;
	}
	public PremioAposta getPremioAposta14() {
		return premioAposta14;
	}
	public void setPremioAposta14(PremioAposta premioAposta14) {
		this.premioAposta14 = premioAposta14;
	}
	public PremioAposta getPremioAposta13() {
		return premioAposta13;
	}
	public void setPremioAposta13(PremioAposta premioAposta13) {
		this.premioAposta13 = premioAposta13;
	}
	public PremioAposta getPremioAposta12() {
		return premioAposta12;
	}
	public void setPremioAposta12(PremioAposta premioAposta12) {
		this.premioAposta12 = premioAposta12;
	}
	public PremioAposta getPremioAposta11() {
		return premioAposta11;
	}
	public void setPremioAposta11(PremioAposta premioAposta11) {
		this.premioAposta11 = premioAposta11;
	}
}
