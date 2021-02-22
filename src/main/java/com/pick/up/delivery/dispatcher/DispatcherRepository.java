package com.pick.up.delivery.dispatcher;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pick.up.delivery.customer.Customer;

@Repository
public interface DispatcherRepository extends JpaRepository<Dispatcher, Long> {
	@Query("SELECT c FROM Dispatcher c WHERE c.username = ?1")
	public Optional<Dispatcher> findDispatcherByUsername(String dispatcherUsername) ;
	
	@Query("SELECT c FROM Dispatcher c WHERE c.email = ?1")
	public Optional<Dispatcher> findDispatcherByEmail(String dispatcherEmail) ;

	@Query("SELECT c FROM Dispatcher c WHERE c.identificationNo = ?1")
	public Optional<Dispatcher> findDispatcherByIdentificationNo(String dispatcherIdentificationNo) ;
	
	@Query("SELECT c FROM Dispatcher c WHERE c.identificationNo = ?1")
	public Optional<Dispatcher> findDispatcherByTelephone(String telephone) ;
	
	//@Query("SELECT c FROM Dispatcher c WHERE c.stateOfOrigin = ?1")
	public List<Dispatcher> findDispatcherByStateOfOrigin(String stateOfOrigin) ;

	//@Query("SELECT c FROM Dispatcher c WHERE c.surname = ?1")
	public List<Dispatcher> findDispatcherBySurname(String surname) ;
}
