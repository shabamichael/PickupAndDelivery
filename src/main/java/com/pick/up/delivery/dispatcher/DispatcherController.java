package com.pick.up.delivery.dispatcher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/dispatcher")
public class DispatcherController {
	
	private DispatcherService dispatcherService;

	@Autowired
	public DispatcherController(DispatcherService dispatcherService) {
		super();
		this.dispatcherService = dispatcherService;
	}
	
	@GetMapping("find/id/{dispatcherId}")
	public ResponseEntity<Dispatcher>   getDispatcherById(@PathVariable("dispatcherId") Long dispatcherId) {
		Dispatcher dispatcher = dispatcherService.getDispatcherById(dispatcherId);
		return new ResponseEntity<Dispatcher>(dispatcher, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Dispatcher>> getAllDispatchers(){
		List<Dispatcher>dispatchers = dispatcherService.getAllDispatchers();
		return new ResponseEntity<List<Dispatcher>>(dispatchers,HttpStatus.OK);
	}
	
	@GetMapping("find/username/{dispatcherUsername}")
	public ResponseEntity<Dispatcher> getDispatcherByUsername(@PathVariable("dispatcherUsername") String dispatcherUsername) {
		Dispatcher dispatcher = dispatcherService.getDispatcherByUsername(dispatcherUsername);
		return new ResponseEntity<Dispatcher>(dispatcher, HttpStatus.OK);
	}
	@GetMapping("find/email/{dispatcherEmail}")
	public ResponseEntity<Dispatcher> getDispatcherByEmail(@PathVariable("dispatcherEmail") String dispatcherEmail){
		Dispatcher dispatcher = dispatcherService.getDispatcherByEmail(dispatcherEmail);
		return new ResponseEntity<Dispatcher>(dispatcher, HttpStatus.OK);
	}
	@GetMapping("find/idno/{dispatcherIdentificationNo}")
	public ResponseEntity<Dispatcher> getDispatcherByIdentificationNo(@PathVariable("dispatcherIdentificationNo") String dispatcherIdentificationNo) {
		Dispatcher dispatcher = dispatcherService.getDispatcherByIdentificationNo(dispatcherIdentificationNo);
		return new ResponseEntity<Dispatcher>(dispatcher, HttpStatus.OK);
	}
	@GetMapping("find/state/{stateOfOrigin}")
	public ResponseEntity<List<Dispatcher>> getDispatcherBystateOfOrigin(@PathVariable("stateOfOrigin") String stateOfOrigin) {
		List<Dispatcher> dispatchers = dispatcherService.getDispatcherBystateOfOrigin(stateOfOrigin);
		return new ResponseEntity<List<Dispatcher>>(dispatchers, HttpStatus.OK);
	}
	@GetMapping("find/surname/{surname}")
	public ResponseEntity<List<Dispatcher>>getDispatcherBySurname(@PathVariable("surname") String surname){
		List<Dispatcher> dispatchers = dispatcherService.getDispatcherBySurname(surname);
		return new ResponseEntity<List<Dispatcher>>(dispatchers, HttpStatus.OK);
	}
	@GetMapping("find/telephone/{surname}")
	public ResponseEntity<Dispatcher> getDispatcherByTelephone(@PathVariable("telephone") String telephone){
		Dispatcher dispatcher = dispatcherService.getDispatcherByTelephone(telephone);
		return new ResponseEntity<Dispatcher>(dispatcher, HttpStatus.OK);
	}
	
	@DeleteMapping(path="delete/{id}")
	public ResponseEntity<Dispatcher> deleteDispatcher(Long id){
		dispatcherService.deleteDispatcher(id);
		return new ResponseEntity<Dispatcher>( HttpStatus.OK);
	}
	@PutMapping(path="update/{dispatcherId}")
	public void setDispatcherInfoById(
			@PathVariable("dispatcherId") Long dispatcherId, 
			@RequestParam(required = false) String telephone, 
			@RequestParam(required = false) String address){
		dispatcherService.setDispatcherInfoById(dispatcherId, telephone, address);
	}

	@GetMapping(path="count")
	public  Long getTotalDispatchersCount() {
		return dispatcherService.getTotalDispatchersCount();
	}


}
