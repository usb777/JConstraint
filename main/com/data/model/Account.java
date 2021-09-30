package com.data.model;

import javax.validation.constraints.*;

public class Account {
	
	@NotNull(message = "Cannot be null")	
	String business_cd;
	
	@NotNull(message = "Cannot be 'null'")
	@NotEmpty(message = "Cannot be 'missing'")
	@Size(min=4, max = 16, message = "Length cannot be greater than 16 characters")
	@Pattern(regexp = "^(?=.*\\d)(?=.*[1-9]).{1,100}$", message = "Cannot have 'all zeros'")
	@Pattern(regexp="^[0-9]+$", message = "Cannot be alpha-numeric")
	String account_number;

	public String getBusiness_cd() {
		return business_cd;
	}

	public void setBusiness_cd(String business_cd) {
		this.business_cd = business_cd;
	}

	public String getAccount_number() {
		return account_number;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}
	
	
	
	
	
	
	
	

}
