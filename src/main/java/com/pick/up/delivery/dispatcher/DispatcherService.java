package com.pick.up.delivery.dispatcher;

import java.util.List;

public interface DispatcherService {
	public abstract Dispatcher getDispatcherById(Long dispatcherId) ;
	public abstract List<Dispatcher> getAllDispatchers();
	public abstract Dispatcher getDispatcherByUsername(String dispatcherUsername) ;
	public abstract Dispatcher getDispatcherByEmail(String dispatcherEmail) ;
	public abstract Dispatcher getDispatcherByIdentificationNo(String dispatcherIdentificationNo) ;
	public abstract List<Dispatcher> getDispatcherBystateOfOrigin(String stateOfOrigin) ;
	public abstract List<Dispatcher>getDispatcherBySurname(String surname);
	public abstract Dispatcher getDispatcherByTelephone(String telephone);
	public abstract void deleteDispatcher(Long id);
	public abstract void setDispatcherInfoById(Long dispatcherId, String telephone, String address);
	public abstract Long getTotalDispatchersCount();

	

	




}
