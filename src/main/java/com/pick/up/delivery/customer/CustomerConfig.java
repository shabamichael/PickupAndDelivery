package com.pick.up.delivery.customer;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class CustomerConfig {
	
	 

	@Bean
	CommandLineRunner commandLineRunner(CustomerRepository customerRepository ) {
		return args->
		{ 
			Customer customer;
			Customer michael =new Customer("Michael Pty Ltd", "shabamichael@customer.com", "michael", "Johannesburg", "0717424906","httpl://localhost", UUID.randomUUID().toString());	
			Customer joy =new Customer("Joy Pty Ltd", "shabajoy@customer.com", "Joy", "Lagos", "0717424906", "httpl://localhost",UUID.randomUUID().toString());	
			Customer blessing =new Customer("Blessing Pty Ltd", "shabablessing@customer.com", "blessing", "Lagos Nigeria", "0717424906", "httpl://localhost",UUID.randomUUID().toString());	

			
			customerRepository.saveAll(List.of(michael, joy, blessing));
};
}
}