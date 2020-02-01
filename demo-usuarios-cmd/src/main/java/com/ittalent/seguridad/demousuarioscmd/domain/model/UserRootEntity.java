package com.ittalent.seguridad.demousuarioscmd.domain.model;

import java.util.ArrayList;
import java.util.List;


public class UserRootEntity {

	private String email;
	private String password;
	List<Phone> phones = new ArrayList<Phone>();
	private String token;
	
	
	
	public UserRootEntity( String email, String password, List<Phone> phones,String token) {
		super();
		this.email = email;
		this.password = password;
		this.phones = phones;
		this.token = token;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Phone> getPhones() {
		return phones;
	}
	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	

}

