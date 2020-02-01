package com.ittalent.seguridad.demousuarioscmd;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ittalent.seguridad.demousuarioscmd.infra.adapters.http.rest.domain.User;

@RunWith(SpringRunner.class)
@SpringBootTest()
@AutoConfigureMockMvc
public class DemoUsuariosCmdApplicationTests {

	@Autowired
    private MockMvc mvc;
	private final ObjectMapper mapper = new ObjectMapper();
	 
 

	@Test
	public void contextLoads() {
	}

	@Test
	public void givenUser_whenCreateUser_thenStatus200()
	  throws Exception {
	 
		User user = new User();
		user.setEmail("test@test.cl");
		user.setPassword("password");
	 
	    mvc.perform(post("/security/v1.0/users")
	      .content(mapper.writeValueAsString(user))
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk())
	      .andExpect(content()
	      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
	      .andExpect(status().is2xxSuccessful());
	    
	}
	
	
	
	
}
