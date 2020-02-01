package com.ittalent.seguridad.demousuarioscmd.domain.model;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

final static Logger log = Logger.getLogger(UserService.class);

UserRepository repository;

public UserService(UserRepository repository) {
	this.repository = repository;
}


public User addUser(UserAggregate userAgg){
	
	User user = new User();
	user.setEmail(userAgg.userRootentity.getEmail());
	user.setPassword(userAgg.userRootentity.getPassword());
	user.setPhones(userAgg.userRootentity.getPhones());
	user.setToken(userAgg.userRootentity.getToken());
	
	User savedUser = this.repository.save(user);
	log.info("User Saved successful - Id : " + savedUser.getId());
	return savedUser;
		
}

}
