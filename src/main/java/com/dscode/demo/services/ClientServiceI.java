package com.dscode.demo.services;

import java.util.Optional;

import com.dscode.demo.repository.Client;

public interface ClientServiceI {

	public Iterable<Client> findAll();

	public Optional<Client> findById(int id);

	public Client createOrUpdate(Client client);

	public void deleteById(int id);

}
