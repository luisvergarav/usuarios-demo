package com.ittalent.seguridad.demousuarioscmd.domain.events;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties({"mapper", "entityType"})
public class UserCreatedIntegrationEvent  implements EventDomain {
	
	private String name;
	private final ObjectMapper mapper = new ObjectMapper();
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	@JsonIgnore
	public String getEntityId() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
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
	
	@Override
	public String getEntityType() {
		return getClass().getName();
	}

	
	public ObjectMapper getMapper() {
		return mapper;
	}

	


	
	
}
