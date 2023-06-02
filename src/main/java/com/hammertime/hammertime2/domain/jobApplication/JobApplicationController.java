package com.hammertime.hammertime2.domain.jobApplication;

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
import com.hammertime.hammertime2.exceptions.JobApplicationNotFoundException;
import com.hammertime.hammertime2.exceptions.JobNotFoundException;
import com.hammertime.hammertime2.exceptions.ProfessionalNotFoundException;
import com.hammertime.hammertime2.service.IBackendService;

@RestController
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class JobApplicationController {
    
    @Autowired
	IBackendService backendService;

    //use case: get all job applications.
	@GetMapping("/dataRequest/jobApplications")
	List<JobApplication> all() {
		return backendService.getJobApplications();
	}
	
	//use case: get all job application ids.
	@GetMapping("/dataRequest/jobApplications/ids")
	List<Long> getJobApplicationIds(){
		return backendService.getJobApplicationIds();
	}
	
    //use case: get job application by id
	@GetMapping("/dataRequest/jobApplication/{id}")
	JobApplication getJobApplicationById(@PathVariable Long id) throws JobApplicationNotFoundException {
		return backendService.getJobApplication(id);
	}

	//use case: create job application
	@PostMapping("/dataRequest/jobApplication")
	JobApplication newJobApplication(@RequestBody JobApplication jobApplication) throws ProfessionalNotFoundException, JobNotFoundException {
		return backendService.createJobApplication(jobApplication);
	}

	//use case: update job application by id
	@PutMapping("/dataRequest/jobApplication/{id}")
	JobApplication replaceJobApplication(@RequestBody JobApplication newJobApplication, @PathVariable Long id) {
		return backendService.updateJobApplication(newJobApplication, id);
	}
	
	//use case: delete job application by id
	@DeleteMapping("/dataRequest/jobApplication/{id}")
	void deleteJobApplication(@PathVariable Long id) {
		backendService.deleteJobApplication(id);
	}

}
