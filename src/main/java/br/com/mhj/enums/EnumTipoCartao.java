package br.com.mhj.enums;

public enum EnumTipoCartao {

	FREE(1, "Free"), 
	PLATINUM(2, "Platinum"),
	NUBANK(3, "Nubank"), 
	INTER(4, "Inter"), 
	RIACHUELO(5,"Riachuelo"), 
	CC(6,"Cc"), 
	EXTRATO_CC(7,"Extrato"),
	DESCONHECIDO(99, "Desconhecido");

	Integer codigo;
	String nome;

	private EnumTipoCartao(Integer codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public static EnumTipoCartao getTipoCartao(String tipoCartao) {
		for (EnumTipoCartao enumTipoCartao : EnumTipoCartao.values()) {
			if (enumTipoCartao.nome.equals(tipoCartao)) {
				return enumTipoCartao;
			}
		}
		return EnumTipoCartao.DESCONHECIDO;
	}

}
