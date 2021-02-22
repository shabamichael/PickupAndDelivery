package com.pick.up.delivery.dispatcher;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pick.up.delivery.customer.Customer;

@Service
public class DispatcherServiceImplementation implements DispatcherService {
	private final Logger LOGGER = LoggerFactory.getLogger(DispatcherServiceImplementation.class);
	private DispatcherRepository dispatcherRepository;

	@Autowired
	public DispatcherServiceImplementation(DispatcherRepository dispatcherRepository) {
		this.dispatcherRepository = dispatcherRepository;
	}

	String message, standardMessage;

//Returns List of all Dispatchers in the repository
	public List<Dispatcher> getAllDispatchers() {
		return dispatcherRepository.findAll();
	}

	// Returns a dispatcher by Id
	public Dispatcher getDispatcherById(Long dispatcherId) {
		return dispatcherRepository.findById(dispatcherId)
				.orElseThrow(() -> new DispatcherNotFoundException(dispatcherId));
	}

	// Returns a dispatcher by username
	@Override
	public Dispatcher getDispatcherByUsername(String dispatcherUsername) {
		return dispatcherRepository.findDispatcherByUsername(dispatcherUsername)
				.orElseThrow(() -> new DispatcherNotFoundException(dispatcherUsername));

	}

	// Returns a dispatcher by email
	@Override
	public Dispatcher getDispatcherByEmail(String dispatcherEmail) {
		return dispatcherRepository.findDispatcherByEmail(dispatcherEmail)
				.orElseThrow(() -> new DispatcherNotFoundException(dispatcherEmail));
	}

	// Returns a dispatcher by Id number
	@Override
	public Dispatcher getDispatcherByIdentificationNo(String dispatcherIdentificationNo) {
		return dispatcherRepository.findDispatcherByIdentificationNo(dispatcherIdentificationNo)
				.orElseThrow(() -> new DispatcherNotFoundException(dispatcherIdentificationNo));
	}

	// Returns a dispatcher by Telephone number
	@Override
	public Dispatcher getDispatcherByTelephone(String telephone) {
		return dispatcherRepository.findDispatcherByTelephone(telephone)
				.orElseThrow(() -> new DispatcherNotFoundException(telephone));
	}

	// Returns a List of dispatchers by state of origin
	@Override
	public List<Dispatcher> getDispatcherBystateOfOrigin(String stateOfOrigin) {
		return dispatcherRepository.findDispatcherByStateOfOrigin(stateOfOrigin);
	}

	// Returns a List of all dispatchers by surname
	@Override
	public List<Dispatcher> getDispatcherBySurname(String surname) {
		return dispatcherRepository.findDispatcherBySurname(surname);
	}

	// Add a new dispatcher to the repository
	public Dispatcher addNewDispatcher(Dispatcher dispatcher) {
		message = "Creating a new dispatcher %s %s ";
		standardMessage = String.format(message, dispatcher.getFirstname(), dispatcher.getSurname());

		Optional<Dispatcher> dispatcherEmail = dispatcherRepository.findDispatcherByEmail(dispatcher.getEmail());
		Optional<Dispatcher> dispaterUsername = dispatcherRepository.findDispatcherByUsername(dispatcher.getUsername());
		Optional<Dispatcher> dispaterIdentificationNo = dispatcherRepository
				.findDispatcherByIdentificationNo(dispatcher.getIdentificationNo());
		Optional<Dispatcher> dispatcherTelephone = dispatcherRepository
				.findDispatcherByTelephone(dispatcher.getTelephone());

		if (dispatcherEmail.isPresent()) {
			message = "email %s is not available";
			standardMessage = String.format(message, dispatcher.getEmail());
			LOGGER.error(standardMessage);
			throw new IllegalStateException(standardMessage);
		}

		else if (dispaterUsername.isPresent()) {
			message = "username %s is not available";
			standardMessage = String.format(message, dispatcher.getUsername());
			LOGGER.error(standardMessage);
			throw new IllegalStateException(standardMessage);
		}

		else if (dispaterIdentificationNo.isPresent()) {
			message = "id number %s  belongs to another person";
			standardMessage = String.format(message, dispatcher.getIdentificationNo());
			LOGGER.error(standardMessage);
			throw new IllegalStateException(standardMessage);
		} else if (dispatcherTelephone.isPresent()) {
			message = "id telephone number %s  belongs to another person";
			standardMessage = String.format(message, dispatcher.getTelephone());
			LOGGER.error(standardMessage);
			throw new IllegalStateException(standardMessage);
		}

		else {
			message = "New dispatcher %s %s has been added at  %s";
			standardMessage = String.format(message, dispatcher.getFirstname(), dispatcher.getSurname(),
					LocalDateTime.now());
			LOGGER.info(standardMessage);
			return dispatcherRepository.save(dispatcher);
		}
	}

	//deletes a dispatcher by id
	@Override
	public void deleteDispatcher(Long id) {
		boolean exists = dispatcherRepository.existsById(id);
		if (!exists) {
			throw new DispatcherNotFoundException(id);
		} else {
			dispatcherRepository.deleteById(id);
		}
	}

	//updates the dispatcher by id
	@Override
	public void setDispatcherInfoById(Long dispatcherId, String dispatchertelephone, String dispatchetrAddress) {
		standardMessage = String.format("Customer with id  %s does not exist",
				dispatcherRepository.getOne(dispatcherId).getId());
		Dispatcher dispatcherFromDb = dispatcherRepository.findById(dispatcherId)
				.orElseThrow(() -> new IllegalStateException(standardMessage));
		if (dispatchertelephone != null && dispatchertelephone.length() > 0
				&& !(Objects.equals(dispatcherFromDb.getTelephone(), dispatchertelephone))) {
			dispatcherFromDb.setTelephone(dispatchertelephone);
			message = "The Dispatcher telephone number was updated to %s  at %s";
			standardMessage = String.format(message, dispatchertelephone, LocalDateTime.now());
			LOGGER.info(standardMessage);
			dispatcherRepository.save(dispatcherFromDb);
		}

		if (dispatchetrAddress != null && dispatchetrAddress.length() > 0
				&& !Objects.equals(dispatcherFromDb.getAddress(), dispatchetrAddress)) {
			dispatcherFromDb.setAddress(dispatchetrAddress);
			message = "The Address was updated to %s  at %s";
			standardMessage = String.format(message, dispatchetrAddress, LocalDateTime.now());
			LOGGER.info(standardMessage);
		}
	}
	//Count of total dispatchers on the system
	@Override
	public Long getTotalDispatchersCount() {
		return dispatcherRepository.count();
	}
}