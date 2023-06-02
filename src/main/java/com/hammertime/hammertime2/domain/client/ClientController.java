package com.hammertime.hammertime2.domain.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hammertime.hammertime2.exceptions.ClientNotFoundException;
import com.hammertime.hammertime2.service.IBackendService;

@RestController
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class ClientController {
	
	@Autowired
	IBackendService backendService;
	
	//use case: get all clients.
	@GetMapping("/dataRequest/clients")
	List<Client> all() {
		return backendService.getClients();
	}

	//use case: get all client ids.
	@GetMapping("/dataRequest/clients/ids")
	List<Long> getClientIds(){
		return backendService.getClientIds();
	}
	
	//use case: create client
	@PostMapping("/dataRequest/client")
	Client newClient(@RequestBody Client client) throws ClientNotFoundException {
		return backendService.createClient(client);
	}

	//use case: update client
	@PutMapping("/dataRequest/client/{id}")
	Client replaceClient(@RequestBody Client newClient, @PathVariable Long id) {
		return backendService.updateClient(newClient, id);
	}
	
	//use case: delete client
	@DeleteMapping("/dataRequest/client/{id}")
	void deleteClient(@PathVariable Long id) {
		backendService.deleteClient(id);
	}
	
	//use case: get client by id
	@GetMapping("/dataRequest/client/{id}")
	Client getClientById(@PathVariable Long id) throws ClientNotFoundException {
		return backendService.getClient(id);
	}

	//use case: update client membership date end
	@PutMapping("/dataRequest/client/{id}/memberEnd/{days}")
	Client replaceClientMemberEnd(@PathVariable Long id, @PathVariable Integer days) throws ClientNotFoundException {
		return backendService.updateClientMemberEnd(id, days);
	}

	@GetMapping("/dataRequest/client/login/{email}/{password}")
	boolean loginClient(@PathVariable String email, @PathVariable String password) throws ClientNotFoundException {
		return backendService.verifyClient(email, password);
	}
}