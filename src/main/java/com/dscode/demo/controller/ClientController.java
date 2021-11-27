package com.dscode.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dscode.demo.repository.Client;
import com.dscode.demo.repository.ClientRepository;
import com.dscode.demo.services.ClientServiceI;

@Controller
@RequestMapping("/api/clients/") // http://localhost:8080/api/clients
public class ClientController {

	@Autowired
	private ClientServiceI clientService;
	
	@Autowired
	private ClientRepository clientRepository;

	// Create a new client
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Client client) {
		return ResponseEntity.status(HttpStatus.CREATED).body(clientService.createOrUpdate(client));
	}

	// Read an client
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable(value = "id") int clientId) {
		Optional<Client> oClient = clientService.findById(clientId);

		if (!oClient.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(oClient);
	}

	// Update an client
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Client clientDetails, @PathVariable(value = "id") int clientId) {
		Optional<Client> client = clientService.findById(clientId);

		if (!client.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		client.get().setName(clientDetails.getName());
		client.get().setSurname1(clientDetails.getSurname1());
		client.get().setSurname2(clientDetails.getSurname2());
		client.get().setDatebirth(clientDetails.getDatebirth());
		client.get().setDni(clientDetails.getDni());

		return ResponseEntity.status(HttpStatus.CREATED).body(clientService.createOrUpdate(client.get()));

	}

	
	@GetMapping("/findAll")
	public String findAll(Model model) {
		List<Client> clientes = clientService.findAll();
		model.addAttribute("clientes", clientes);
		return "index";
	}
	
	
}
