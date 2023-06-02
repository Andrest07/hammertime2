package com.hammertime.hammertime2.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hammertime.hammertime2.domain.client.Client;
import com.hammertime.hammertime2.domain.job.Job;
import com.hammertime.hammertime2.domain.professional.Professional;
import com.hammertime.hammertime2.domain.rating.Rating;
import com.hammertime.hammertime2.exceptions.ClientNotFoundException;
import com.hammertime.hammertime2.service.IBackendService;

@RestController
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class LoadDatabaseController {
	
	@Autowired
	IBackendService backendService;
	
	//use case: populate client with num.
	@PostMapping("/dataRequest/populate/client/{num}")
	List<Client> popClient(@PathVariable Integer num) throws ClientNotFoundException {
		backendService.populateClient(num);
		return backendService.getClients();
	}

	//use case: populate professional with num.
	@PostMapping("/dataRequest/populate/professional/{num}")
	List<Professional> popProfessional(@PathVariable Integer num) {
		backendService.populateProfessional(num);
		return backendService.getProfessionals();
	}
	
	//use case: populate job with num.
	@PostMapping("/dataRequest/populate/job/{num}")
	List<Job> popJob(@PathVariable Integer num) {
		backendService.populateJob(num);
		return backendService.getJobs();
	}

	//use case: populate job with num.
	@PostMapping("/dataRequest/populate/rating")
	List<Rating> popJob() {
		backendService.populateRating();
		return backendService.getRatings();
	}

	//use case: populate all with num.
	@PostMapping("/dataRequest/populate/all/{num}")
	void popAll(@PathVariable Integer num) throws ClientNotFoundException {
		backendService.populateAll(num);
	}
}