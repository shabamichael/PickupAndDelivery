package com.pick.up.delivery.customer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	@Query("SELECT c FROM Customer c WHERE c.email = ?1")
	public abstract Optional <Customer> findCustomerByEmail(String email);

	@Query("SELECT c FROM Customer c WHERE c.username = ?1")
	public abstract Optional<Customer> findCustomerByUsername(String username);
	
	//@Query("SELECT b FROM Customer b WHERE b.businessName = ?1")
	//public abstract Optional<Customer>findCustomerByBusinessname(String businessName);

	@Modifying
	@Query("update Customer u set u.businessName = ?1, u.email = ?2, u.address = ?3, u.telephone = ?4 where u.id = ?3")
	void setCustomerInfoById(String firstname, String lastname, Integer userId);
	
}
