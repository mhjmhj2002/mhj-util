package br.com.mhj.enums;

public enum EnumTipoLancamento {
	CREDITO(1, "Credito"), DEBITO(2, "Debito");

	private Integer id;
	private String descricao;

	private EnumTipoLancamento(Integer id, String descricao) {
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
