//package com.ittalent.seguridad.demousuarioscmd.application.adapters;
//
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.ittalent.seguridad.demousuarioscmd.controllers.UsuariosController;
//import com.ittalent.seguridad.demousuarioscmd.domain.events.UserCreatedIntegrationEvent;
//import com.ittalent.seguridad.demousuarioscmd.domain.ports.CommandBus;
//
//@Component
//public class DecoratorCreateUserCommandBus implements CommandBus<CreateUserCommandImpl> {
//	
//
//	@Autowired
//	CreateUserCommandBus bus;
//	final static Logger log = Logger.getLogger(DecoratorCreateUserCommandBus.class);
//	
//    public DecoratorCreateUserCommandBus(CreateUserCommandBus bus) {
//    	this.bus = bus;
//    }
//    
//
//    	@Override
//	public boolean execute(CreateUserCommandImpl command) throws Exception {
//
//
//    		UserCreatedIntegrationEvent integrationEvent = new UserCreatedIntegrationEvent();
//    		try {
//    			
//    			integrationEvent.setName(command.getName());
//    			
//    	        if  (this.bus.execute(command)) {
//    	            log.info("Sending UserCreatedEvent integration Event "  + command.getName());
//    	       	 
//    	            return true;    	        
//    			}        
//    		} catch (Exception e) {
//    			log.error("Error Sending UserCreatedEvent integration Event " + integrationEvent.getMetadata()  + e.getLocalizedMessage());
//    			throw e;
//    		}
//
//    		
//    	
//		
//		return false;
//	}
//
//
//	}