package com.dscode.demo.services;

import java.util.List;
import java.util.Optional;

import com.dscode.demo.repository.Client;

public interface ClientServiceI {

	public List<Client> findAll();
	
	public Optional<Client> findById(int id);
	
	public Client createOrUpdate(Client client);
	
	public void deleteById(int id);
	
}
