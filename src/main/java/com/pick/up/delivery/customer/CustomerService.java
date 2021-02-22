package com.pick.up.delivery.customer;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerService {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);
	private String message, standardMessage;
	private final static  String USER_NOT_FOUND_MSG = "User with email %s not found";
	private final static  String USER_NOT_FOUND_MSG1 = "User with username %s not found";
	private final static  String USER_NOT_FOUND_MSG2 = "User with id %s not found";


	
	private  CustomerRepository  customerRepository;
	@Autowired
	public CustomerService(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}


	//Add a  new customer to the repository
	public Customer addNewCustomer(Customer customer) {
		customer.setCustomerCode(UUID.randomUUID().toString());
		message = "Creating a new user %s ";
		standardMessage = String.format(message, customer.getUsername());
		Optional<Customer>customerEmail = customerRepository.findCustomerByEmail(customer.getEmail());
		Optional<Customer>customerUsername = customerRepository.findCustomerByUsername(customer.getUsername());
		if(customerEmail.isPresent() ) {
			message = "email %s is not available";
			standardMessage = String.format(message, customer.getEmail());
			LOGGER.error(standardMessage);
			throw new IllegalStateException(standardMessage);
		}
		else if(customerUsername.isPresent() ) {
			message = "username %s is not available";
			standardMessage = String.format(message, customer.getUsername());
			LOGGER.error(standardMessage);
			throw new IllegalStateException(standardMessage);
		}
		else {
			message ="New customer %s has been added at  %s";
			standardMessage = String.format(message, customer.getBusinessName(), LocalDateTime.now());
		LOGGER.info(standardMessage);
		return customerRepository.save(customer);
		}
	}		
		
			
	//Get List of all customers
	public List<Customer>getAllCustomers(){
		return customerRepository.findAll(); 
	}
	
	//Get Just one customer by email
	public Customer getCustomerByEmail(String email) {
		return customerRepository.findCustomerByEmail(email)
				.orElseThrow(()-> new IllegalStateException(String .format(USER_NOT_FOUND_MSG,email)));
	}
	
	//Get Just one customer by username
		public Customer getCustomerByUsername(String username) {
			return customerRepository.findCustomerByUsername(username)
					.orElseThrow(()-> new IllegalStateException(String .format(USER_NOT_FOUND_MSG,username)));
		}
		
		//Delete a customer
		public void deleteCustomer(Long id) {
			boolean exists = customerRepository.existsById(id);
					if(!exists) {
						throw new IllegalStateException(String.format("Customer with id number:- %s  does not exist." , id));	
					}
					else {
						customerRepository.deleteById(id);
					}
		}
		
		//Get customer by business name
		public List<Customer> findCustomerByBusinessname(String businessName) {
			List <Customer> customerName = customerRepository.findAll()
					.stream().filter(name ->  name.getBusinessName()
							.equalsIgnoreCase(businessName)).sorted()
					 .collect(Collectors.toList());
			return customerName;
		}
		
		// update a customer
		@Transactional
		public void setCustomerInfoById( Long customerId, 
				String businessName, 
				String address, 
				String telephone, 
				String email, 
				String username) {
			standardMessage = String.format("Customer with id  %s does not exist", customerRepository.getOne(customerId).getId());	
			Customer customerFromDb = customerRepository.findById(customerId)
					.orElseThrow(() -> new IllegalStateException(standardMessage));
			if(businessName != null && businessName.length() > 0 && !(Objects.equals(customerFromDb.getBusinessName(),businessName))) 
			{
					customerFromDb.setBusinessName(businessName);
					message = "The Business name was updated to %s  at %s";
					standardMessage = String.format(message, businessName, LocalDateTime.now());
					LOGGER.info(standardMessage);
				    customerRepository.save(customerFromDb);

			}

			if(address != null && 
					address.length() > 0 &&
					!Objects.equals(customerFromDb.getAddress(),address)) 
			{
					customerFromDb.setAddress(address);
					message = "The Address was updated to %s  at %s";
					standardMessage = String.format(message, address, LocalDateTime.now());
					LOGGER.info(standardMessage);
			}
			
			if(telephone != null && 
					telephone.length() > 0 &&
					!Objects.equals(customerFromDb.getTelephone(),telephone)) 
			{
					customerFromDb.setTelephone(telephone);
					message = "The Telephone number was updated to %s  at %s";
					standardMessage = String.format(message, telephone, LocalDateTime.now());
					LOGGER.info(standardMessage);
			}
		
			if(email != null && 
					email.length() > 0 &&
					!Objects.equals(customerFromDb.getEmail(),email)) 
			{
				Optional<Customer>customerOptional  = customerRepository
						.findCustomerByEmail(email);
				if(customerOptional.isPresent()) {
					throw new IllegalStateException("email taken");
				}
				
					customerFromDb.setEmail(email);
					message = "The email was updated to %s  at %s";
					standardMessage = String.format(message, email, LocalDateTime.now());
					LOGGER.info(standardMessage);
			}
			
			if(username != null && 
					username.length() > 0 &&
					!Objects.equals(customerFromDb.getUsername(), username)) 
			{
				Optional<Customer>customerOptional  = customerRepository
						.findCustomerByUsername(username);
				if(customerOptional.isPresent()) {
					throw new IllegalStateException("username taken");
				}
					customerFromDb.setUsername(username);
					message = "The username was updated to %s  at %s";
					standardMessage = String.format(message, username, LocalDateTime.now());
					LOGGER.info(standardMessage);
			}
		}

		//Get total customers in db
		public Long getTotalCustomerCount() {
			return customerRepository.count();
		}


		//Get customer by id
	public Customer getCustomerById(Long id) {
		return customerRepository.findById(id).orElseThrow(()-> new IllegalStateException(String.format(USER_NOT_FOUND_MSG2, id)));
		
	}
	
	
}