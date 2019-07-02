package br.com.mhj.enums;

public enum EnumLancamento {
	COMPRA_CARTAO_MAESTRO(1, "COMPRA CARTAO MAESTRO", EnumTipoLancamento.DEBITO);

	private Integer id;
	private String descricao;
	private EnumTipoLancamento tipoLancamento;

	private EnumLancamento(Integer id, String descricao, EnumTipoLancamento tipoLancamento) {
		this.id = id;
		this.descricao = descricao;
		this.tipoLancamento = tipoLancamento;
	}

	public Integer getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public EnumTipoLancamento getTipoLancamento() {
		return tipoLancamento;
	}

}
