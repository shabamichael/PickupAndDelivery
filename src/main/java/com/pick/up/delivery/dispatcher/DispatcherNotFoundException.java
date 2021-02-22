package com.pick.up.delivery.dispatcher;

public class DispatcherNotFoundException extends RuntimeException {
	DispatcherNotFoundException(Long id){
	super(String.format("The dispatcher with id %s not found" , id));
	}
	DispatcherNotFoundException(String user){
		super(String.format("The dispatcher  %s not found" , user));

	}
	
	
	

}
