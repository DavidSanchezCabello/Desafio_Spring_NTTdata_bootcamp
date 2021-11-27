package com.dscode.demo.services;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dscode.demo.repository.Client;
import com.dscode.demo.repository.ClientRepository;

@Service
public class ClienteServiceImpl implements ClientServiceI{

	@Autowired
	private ClientRepository clientRepository;
	

	@Override
	@Transactional(readOnly = true)
	public Iterable<Client> findAll() {
		return clientRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Client> findById(int id) {
		return clientRepository.findById(id);
	}

	@Override
	@Transactional
	public Client createOrUpdate(Client client) {
		return clientRepository.save(client);
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		clientRepository.deleteById(id);		
	}
	
}
