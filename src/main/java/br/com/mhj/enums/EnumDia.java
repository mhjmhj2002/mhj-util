package br.com.mhj.enums;

public enum EnumDia {
	D1(1,"01",""),
	D2(2,"02",""),
	D3(3,"03",""),
	D4(4,"04",""),
	D5(5,"05",""),
	D6(6,"06",""),
	D7(7,"07",""),
	D8(8,"08",""),
	D9(9,"09",""),
	D10(10,"10",""),
	D11(11,"11",""),
	D12(12,"12",""),
	D13(13,"13",""),
	D14(14,"14",""),
	D15(15,"15",""),
	D16(16,"16",""),
	D17(17,"17",""),
	D18(18,"18",""),
	D19(19,"19",""),
	D20(20,"20",""),
	D21(21,"21",""),
	D22(22,"22",""),
	D23(23,"23",""),
	D24(24,"24",""),
	D25(25,"25",""),
	D26(26,"26",""),
	D27(27,"27",""),
	D28(28,"28",""),
	D29(29,"29",""),
	D30(30,"30",""),
	D31(31,"31","");
	
	private int codigo;
	private String numeral;
	private String nome;
	
	private EnumDia(int codigo, String numeral, String nome) {
		this.codigo = codigo;
		this.numeral = numeral;
		this.nome = nome;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNumeral() {
		return numeral;
	}

	public void setNumeral(String numeral) {
		this.numeral = numeral;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public static EnumDia getDiaByCodigo(int dia) {
		for (EnumDia enumDia : EnumDia.values()) {
			if (enumDia.getCodigo() == dia) {
				return enumDia;
			}
		}
		return null;
	}
}
