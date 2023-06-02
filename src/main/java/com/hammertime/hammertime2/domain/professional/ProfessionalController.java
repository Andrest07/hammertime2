package com.hammertime.hammertime2.domain.professional;

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
import com.hammertime.hammertime2.exceptions.ProfessionalNotFoundException;
import com.hammertime.hammertime2.service.IBackendService;

@RestController
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class ProfessionalController {
	
	@Autowired
	IBackendService backendService;
	
	//use case: get all professionals.
	@GetMapping("/dataRequest/professionals")
	List<Professional> all() {
		return backendService.getProfessionals();
	}

	//use case: get all professional ids.
	@GetMapping("/dataRequest/professionals/ids")
	List<Long> getProfessionalIds(){
		return backendService.getProfessionalIds();
	}
	
	//use case: create professional
	@PostMapping("/dataRequest/professional")
	Professional newProfessional(@RequestBody Professional professional) {
		return backendService.createProfessional(professional);
	}

	//use case: update professional
	@PutMapping("/dataRequest/professional/{id}")
	Professional replaceProfessional(@RequestBody Professional newProfessional, @PathVariable Long id) {
		return backendService.updateProfessional(newProfessional, id);
	}
	
	//use case: delete professional
	@DeleteMapping("/dataRequest/professional/{id}")
	void deleteProfessional(@PathVariable Long id) {
		backendService.deleteProfessional(id);
	}
	
	//use case: get professional by id
	@GetMapping("/dataRequest/professional/{id}")
	Professional getProfessionalById(@PathVariable Long id) throws ProfessionalNotFoundException {
		return backendService.getProfessional(id);
	}

	//use case: update professional membership date end
	@PutMapping("/dataRequest/professional/{id}/memberEnd/{days}")
	Professional replaceProfessionalMemberEnd(@PathVariable Long id, @PathVariable Integer days) throws ProfessionalNotFoundException {
		return backendService.updateProfessionalMemberEnd(id, days);
	}

	@GetMapping("/dataRequest/professional/login/{email}/{password}")
	boolean loginProfessional(@PathVariable String email, @PathVariable String password) throws ProfessionalNotFoundException {
		return backendService.verifyProfessional(email, password);
	}

	/*@PutMapping("/professional/{pId}/{rId}")
	Professional putProfessionalRating(@PathVariable Long pId, @PathVariable Long rId) throws ProfessionalNotFoundException {
		return backendService.addProfessionalRating(pId, rId);
	}*/
}