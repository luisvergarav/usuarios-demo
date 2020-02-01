package com.ittalent.seguridad.demousuarioscmd.application.adapters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ittalent.seguridad.demousuarioscmd.domain.model.UserAggregate;
import com.ittalent.seguridad.demousuarioscmd.domain.model.UserService;

@Service
public class UserServiceApplicationImpl {

	@Autowired
	UserService service;
	UserAggregate aggregate;
	
	
	public boolean addUser(CreateUserCommandImpl cmd) {
		List<com.ittalent.seguridad.demousuarioscmd.domain.model.Phone> 
		phoneList = new ArrayList<com.ittalent.seguridad.demousuarioscmd.domain.model.Phone>();
		
		for(Phone phone: cmd.getPhones()) {
			com.ittalent.seguridad.demousuarioscmd.domain.model.Phone 
			newPhone = new com.ittalent.seguridad.demousuarioscmd.domain.model.Phone();
			newPhone.setCityCode(phone.getCityCode());
			newPhone.setCountryCode(phone.getCountryCode());
			newPhone.setNumber(phone.getNumber());
			phoneList.add(newPhone);
			
		}
		aggregate = new  UserAggregate.Builder()
				.email(cmd.getEmail())
				.password(cmd.getPassword())
				.phones(phoneList)
				.token(cmd.getToken())
				.build();
		
		cmd.setIdUsuario(this.aggregate.addUser(service).getId().toString());
		 
		return true;

	}

}
