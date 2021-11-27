package com.dscode.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dscode.demo.repository.Client;
import com.dscode.demo.repository.ClientRepository;

@Service
public class ClienteServiceImpl implements ClientServiceI{

	@Autowired
	private ClientRepository clientRepository;
		
	@Override
	public List<Client> findAll() {		
		return (List<Client>) clientRepository.findAll();
	}

	@Override
	public Optional<Client> findById(int id) {
		return clientRepository.findById(id);
	}

	@Override
	public Client createOrUpdate(Client client) {
		return clientRepository.save(client);
	}

	@Override
	public void deleteById(int id) {
		clientRepository.deleteById(id);		
	}

	
}
