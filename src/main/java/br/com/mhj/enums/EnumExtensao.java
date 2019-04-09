package br.com.mhj.enums;

public enum EnumExtensao {

	PDF(1, ".pdf"),
	CSV(2, ".csv"),
	XLS(3, ".xls");

	Integer codigo;
	String extensao;

	private EnumExtensao(Integer codigo, String extensao) {
		this.codigo = codigo;
		this.extensao = extensao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getExtensao() {
		return extensao;
	}

}
