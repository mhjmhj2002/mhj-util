package br.com.mhj.enums;

public enum EnumType {
	
	EXPENSIVE("G","Gasto"),
	INCOME("E", "Entrada");
	
	private String id;
	private String descrption;
	
	private EnumType(String id, String descrption) {
		this.id = id;
		this.descrption = descrption;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescrption() {
		return descrption;
	}

	public void setDescrption(String descrption) {
		this.descrption = descrption;
	}

}
