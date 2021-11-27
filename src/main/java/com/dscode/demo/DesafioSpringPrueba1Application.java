package com.dscode.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dscode.demo.repository.Client;
import com.dscode.demo.services.ClienteServiceImpl;

@SpringBootApplication
public class DesafioSpringPrueba1Application implements CommandLineRunner {

	@Autowired
	ClienteServiceImpl clientService;

	public static void main(String[] args) {
		SpringApplication.run(DesafioSpringPrueba1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Client client = new Client();
		Client client1 = new Client();
		Client client2 = new Client();

		client.setName("David");
		client.setSurname1("Sánchez");
		client.setSurname2("Cabello");
		client.setDatebirth("1977-04-22");
		client.setDni("12544785T");
		
		client1.setName("Aitor");
		client1.setSurname1("Espi");
		client1.setSurname2("Pérez");
		client1.setDatebirth("1983-10-18");
		client1.setDni("72544896P");

		client2.setName("Arancha");
		client2.setSurname1("Palacios");
		client2.setSurname2("Espi");
		client2.setDatebirth("1982-12-26");
		client2.setDni("74245163T");
		
		clientService.createOrUpdate(client);
		clientService.createOrUpdate(client1);
		clientService.createOrUpdate(client2);
		System.out.println(clientService.findAll());
		System.out.println("MOSTRAR CLIENTES" + clientService.toString());

	}

}
