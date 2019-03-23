package br.com.mhj.pdf;

public enum EnumTipoCartao {

	FREE(1, "free"), PLATINUM(2, "platinum"), NUBANK(3, "nubank"), INTER(4, "inter"), RIACHUELO(5,"riachuelo"), DESCONHECIDO(99, "desconhecido");

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
