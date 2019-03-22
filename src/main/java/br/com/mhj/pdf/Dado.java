package br.com.mhj.pdf;

public class Dado {
	
	private String type;
	private String date;
	private String item;
	private String amount;
	private String parentCategory;
	private String category;
	private String accType;
	private String account;
	private String notes;
	private String label;
	private String status;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getParentCategory() {
		return parentCategory;
	}
	public void setParentCategory(String parentCategory) {
		this.parentCategory = parentCategory;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getAccType() {
		return accType;
	}
	public void setAccType(String accType) {
		this.accType = accType;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCsvLine() {
		StringBuilder builder = new StringBuilder();
		builder.append(type);
		builder.append(";");           
		builder.append(date);   
		builder.append(";");           
		builder.append(item);   
		builder.append(";");           
		builder.append(amount);     
		builder.append(";");       
		builder.append(parentCategory); 
		builder.append(";");   
		builder.append(category);    
		builder.append(";");      
		builder.append(accType);  
		builder.append(";");         
		builder.append(account);   
		builder.append(";");        
		builder.append(notes);    
		builder.append(";");         
		builder.append(label);       
		builder.append(";");      
		builder.append(status);    
		return builder.toString();
	}

}
