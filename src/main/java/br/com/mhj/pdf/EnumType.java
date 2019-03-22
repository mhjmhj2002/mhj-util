package br.com.mhj.pdf;

public enum EnumType {
	
	EXPENSIVE("G","Gastos"),
	INCOME("E", "Entradas");
	
	String id;
	String descrption;
	
	private EnumType(String id, String descrption) {
		this.id = id;
		this.descrption = descrption;
	}

}
