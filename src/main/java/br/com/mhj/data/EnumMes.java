package br.com.mhj.data;

public enum EnumMes {
	
	JAN(1,"01","JAN",""),
	FEV(2,"02","FEV",""),
	MAR(3,"03","MAR",""),
	ABR(4,"04","ABR",""),
	MAI(5,"05","MAI",""),
	JUN(6,"06","JUN",""),
	JUL(7,"07","JUL",""),
	AGO(8,"08","AGO",""),
	SET(9,"09","SET",""),
	OUT(10,"10","OUT",""),
	NOV(11,"11","NOV",""),
	DEZ(12,"12","DEZ","");
	
	private int codigo;
	private String numeral;
	private String abreviacao;
	private String nome;
	
	private EnumMes(int codigo, String numeral, String abreviacao, String nome) {
		this.codigo = codigo;
		this.numeral = numeral;
		this.abreviacao = abreviacao;
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

	public String getAbreviacao() {
		return abreviacao;
	}

	public void setAbreviacao(String abreviacao) {
		this.abreviacao = abreviacao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public static EnumMes getMesByCodigo(int mes) {
		for (EnumMes enumMes : EnumMes.values()) {
			if (enumMes.getCodigo() == mes) {
				return enumMes;
			}
		}
		return null;
	}
}
