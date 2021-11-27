package com.dscode.demo.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dscode.demo.repository.Client;
import com.dscode.demo.services.ClientServiceI;

@Controller
@RequestMapping("/api/clients") // http://localhost:8080/api/clients
public class ClientController {

	@Autowired
	private ClientServiceI clientService;

	// Create a new client
	@PostMapping("/save")
	public ResponseEntity<?> create(@RequestBody Client client) {
		return ResponseEntity.status(HttpStatus.CREATED).body(clientService.createOrUpdate(client));
	}

	// Read an client
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@Valid @PathVariable(value = "id") int clientId) {
		Optional<Client> optionalClient = clientService.findById(clientId);

		if (!optionalClient.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(optionalClient);
	}

	// Update an client
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Client clientDetails, @PathVariable(value = "id") Integer clientId) {
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
	
	// Delete an User
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@Valid @PathVariable(value = "id") Integer clientId){
		
		if(!clientService.findById(clientId).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		clientService.deleteById(clientId);
		return ResponseEntity.ok().build();
	}

	// Read all Clients
	@GetMapping
	public List<Client> readAll(){
		
		List<Client> clients = StreamSupport
				.stream(clientService.findAll().spliterator(), false)
				.collect(Collectors.toList());
		
		return clients;
	}
	
	// Mostrar interfaz
	@GetMapping("/findAll")
	@ExceptionHandler(IOException.class)
	public String findAll(Model model) {
		List<Client> clientes = (List<Client>) clientService.findAll();
		model.addAttribute("clientes", clientes);
		return "index";
	}
	
	@GetMapping("/new")
	@ExceptionHandler(IOException.class)
	public String agregar(Model model) {
		model.addAttribute("cliente", new Client());
		return "form";
	}
	
}
