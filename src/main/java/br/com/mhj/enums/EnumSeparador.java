package br.com.mhj.enums;

public enum EnumSeparador {
	BARRA(1,"/", "barra"),
	TRACO(2,"-", "traco"),
	UNDERLINE(3,"_","underline");
	
	private int codigo;
	private String simbolo;
	private String nome;
	
	private EnumSeparador(int codigo, String simbolo, String nome) {
		this.codigo = codigo;
		this.simbolo = simbolo;
		this.nome = nome;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
