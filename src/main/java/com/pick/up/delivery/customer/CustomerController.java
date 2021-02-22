package com.pick.up.delivery.customer;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "api/v1/customer")
public class CustomerController {
	private final static Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
	private String message, standardMessage;
	private CustomerService customerService;
	@Autowired
	public CustomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}
	
	@Autowired
	CustomerRepository customerRepository;

	@GetMapping
	public ResponseEntity<List<Customer>>getAllCustomers(){
		List<Customer> customers = customerService.getAllCustomers();
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK); 
	}

	
	@GetMapping("/find/businessname/{businessname}")
	public ResponseEntity<List<Customer>>getAllCustomersByBusinessName(@PathVariable("businessname") String businessname){
		List<Customer> customers = customerService.findCustomerByBusinessname(businessname);
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK); 
	}
	
	@GetMapping("/find/{emai}")
	public ResponseEntity<Customer> getCustomerByEmail(@PathVariable("email") String email) {
		Customer customer = customerService.getCustomerByEmail(email);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);	
	}
	
	@GetMapping("/find/{username}")
	public ResponseEntity<Customer> getCustomerByUsername(@PathVariable("username") String username) {
		Customer customer = customerService.getCustomerByUsername(username);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);	
	}
	
	@GetMapping("find/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Long id) {
		Customer customer = customerService.getCustomerById(id);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);	
	}
	
	@PostMapping
	public ResponseEntity<Customer>addNewCustomer(@RequestBody Customer customer) {
		 Customer newCustomer = customerService.addNewCustomer(customer);
		return new ResponseEntity<Customer>(newCustomer, HttpStatus.CREATED);	
	}
	
	@GetMapping(path="count")
	public Long getTotalCustomers() {
		return customerService.getTotalCustomerCount();
	}
	
	
	@DeleteMapping(path="delete/{id}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") Long id) {
		customerService.deleteCustomer(id);
		return new ResponseEntity<Customer>(HttpStatus.OK);
	}
	
	
	@PutMapping(path="update/{id}")
	public void updateCustomer(
			@PathVariable("id") Long id, 
			@RequestParam(required = false) String businessName, 
			@RequestParam (required = false) String address, 
			@RequestParam (required = false) String telephone, 
			@RequestParam (required = false) String email,
			@RequestParam (required = false) String username){
		customerService.setCustomerInfoById(id, businessName, address, telephone, email, username);
	}
}
