package br.com.mhj.enums;

public enum EnumType {
	
	EXPENSIVE("G","Expense"),
	INCOME("E", "Income");
	
	private String id;
	private String description;
	
	private EnumType(String id, String description) {
		this.id = id;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
