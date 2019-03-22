package br.com.mhj.csv;

public enum EnumMes {
	JAN(1,"JAN"),
	FEV(2,"FEV"),
	MAR(3,"MAR"),
	ABR(4,"ABR"),
	MAI(5,"MAI"),
	JUN(6,"JUN"),
	JUL(7,"JUL"),
	AGO(8,"AGO"),
	SET(9,"SET"),
	OUT(10,"OUT"),
	NOV(11,"NOV"),
	DEZ(12,"DEZ"),
	DESCONHECIDO(13,"DES");
	
	Integer codigo;
	String mes;
	
	private EnumMes(Integer codigo, String mes) {
		this.codigo = codigo;
		this.mes = mes;
	}
	
	public static EnumMes getMes(String mes) {
		for (EnumMes enumMes : EnumMes.values()) {
			if (enumMes.mes.equals(mes)) {
				return enumMes;
			}
		}
		return EnumMes.DESCONHECIDO;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getMes() {
		return mes;
	}

}
