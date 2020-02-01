package com.ittalent.seguridad.demousuarioscmd.domain.model;

import java.util.ArrayList;
import java.util.List;
public class UserAggregate {

	final UserRootEntity  userRootentity;
	
	public UserAggregate(Builder builder) {
		this.userRootentity = new UserRootEntity(
				builder.email,
				builder.password,
				builder.phones,
				builder.token
				);
		
	}
	
	public boolean isValid() {
		return true;
	}
	
	public User addUser(UserService service) {
		return service.addUser(this);
	}
	
	

	public static class Builder{
		private String email;
		private String password;
		List<Phone> phones = new ArrayList<Phone>();	
		private String token;
		
		public Builder email(String email) {
			this.email = email;
			return this;
		}
		
		public Builder password(String password) {
			this.password = password;
			return this;
		}
		
		public Builder phones(List<Phone> phones) {
			this.phones = phones;
			return this;
		}
		
		public Builder token(String token) {
			this.token = token;
			return this;
		}
		
		public UserAggregate build() {
			
			return new UserAggregate(this);
		}
				
	}
}
