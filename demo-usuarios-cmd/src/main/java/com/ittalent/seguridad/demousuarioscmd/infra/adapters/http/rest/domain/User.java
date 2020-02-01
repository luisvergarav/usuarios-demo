package com.ittalent.seguridad.demousuarioscmd.infra.adapters.http.rest.domain;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ittalent.seguridad.demousuarioscmd.application.adapters.Phone;

@JsonIgnoreProperties({"mapper"})
public class User {
	private String name;
	@Pattern(message = "Not a valid Email",regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
	private String email;
	
	@Pattern(message = "Not a valid Password",regexp = "((?=.*[a-z])(?=.*[A-Z]{1})(.*[0-9]{1})(.*[0-9]{1}))")
	private String password;
	List<Phone> phones = new ArrayList<Phone>();
	
	
	private final ObjectMapper mapper = new ObjectMapper();
	
	@NotNull
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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


	@JsonIgnore
	public String getMetadata() {
		String jsonValue;
        try {
            jsonValue = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            jsonValue = super.toString();
        }
        return jsonValue;
	}

}
