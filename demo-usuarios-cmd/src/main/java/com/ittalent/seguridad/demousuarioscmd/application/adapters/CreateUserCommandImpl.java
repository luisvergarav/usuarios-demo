package com.ittalent.seguridad.demousuarioscmd.application.adapters;
import java.util.ArrayList;
import java.util.List;

import com.ittalent.seguridad.demousuarioscmd.domain.ports.CreateUserCommand;
import com.ittalent.seguridad.demousuarioscmd.infra.adapters.http.rest.domain.User;


public class CreateUserCommandImpl implements CreateUserCommand<User> {

	private String name;
	private String email;
	private String password;
	List<Phone> phones = new ArrayList<Phone>();
	private String idUsuario;
	private String token;
	
	public CreateUserCommandImpl(User request) {
		this.email = request.getEmail();
		this.name = request.getName();
		this.password = request.getPassword();
		for(Phone phone : request.getPhones()) {
			Phone newPhone = new Phone();
			newPhone.setCityCode(phone.getCityCode());
			newPhone.setCountryCode(phone.getCountryCode());
			newPhone.setNumber(phone.getNumber());
			this.phones.add(newPhone);
		}
	}
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
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

	
}