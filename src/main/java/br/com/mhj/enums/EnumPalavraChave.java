package br.com.mhj.enums;

public enum EnumPalavraChave {

	SALDO_EM(1, "SALDO EM");

	private Integer id;
	private String descricao;

	private EnumPalavraChave(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

}
