package br.com.fabio.studentapi.enums;

public enum PhoneType {
	
	HOME("HOME"), COMMERCIAL("COMMERCIAL"), MOBILE("MOBILE");
	
	private String type;
	
	private PhoneType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}
	
}
