package com.mhj.jogos.domain;

//import org.springframework.security.core.GrantedAuthority;

public class Role /*implements GrantedAuthority*/ {

//	private static final long serialVersionUID = 1L;
	
	private String nome;

	public Role() {
	}
	
	public Role(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

//	@Override
//	public String getAuthority() {
//		return this.nome;
//	}
	
}
