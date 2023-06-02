package com.hammertime.hammertime2.domain.job;

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
import com.hammertime.hammertime2.exceptions.JobNotFoundException;
import com.hammertime.hammertime2.service.IBackendService;

@RestController
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class JobController {
	
	@Autowired
	IBackendService backendService;
	
	//use case: get all jobs.
	@GetMapping("/dataRequest/jobs")
	List<Job> all() {
		return backendService.getJobs();
	}

	//use case: get all job IDs
	@GetMapping("/dataRequest/jobs/ids")
	List<Long> getJobIds(){
		return backendService.getJobIds();
	}

	//use case: search for job by ID
	@GetMapping("/dataRequest/jobs/id/{id}")
	Job getJobById(@PathVariable Long id){
		return backendService.getJob(id);
	}

	//use case: search for job by ProfessionalID
	@GetMapping("/dataRequest/jobs/professionalId/{id}")
	List<Job> getJobByProfIds(@PathVariable Long id){
		return backendService.getProfessionalJobs(id);
	}

	//use case: search for job by clientID
	@GetMapping("/dataRequest/jobs/clientId/{id}")
	List<Job> getJobByClientIds(@PathVariable Long id){
		return backendService.getClientJobs(id);
	}

	//use case: create job
	@PostMapping("/dataRequest/job")
	Job newClient(@RequestBody Job job) {
		return backendService.createJob(job);
	}

	// MUTATORS
	//use case: change title of job
	@PutMapping("/dataRequest/job/{id}/title/{title}")
	void replaceJobTitle(@PathVariable Long id, @PathVariable String title) throws JobNotFoundException {
		backendService.setJobTitle(id, title);
	}
	//use case: change category of job
	@PutMapping("/dataRequest/job/{id}/category/{category}")
	void replaceJobCategory(@PathVariable Long id, @PathVariable String category) throws JobNotFoundException {
		backendService.setJobCategory(id, category);
	}
	//use case: change description of job
	@PutMapping("/dataRequest/job/{id}/description/{description}")
	void replaceJobDescription(@PathVariable Long id, @PathVariable String description) throws JobNotFoundException {
		backendService.setJobDescription(id, description);
	}
	//use case: change status of job
	@PutMapping("/dataRequest/job/{id}/status/{status}")
	void replaceJobStatus(@PathVariable Long id, @PathVariable String status) throws JobNotFoundException {
		backendService.updateJobStatus(id, status);
	}
	//use case: change professional of job
	@PutMapping("/dataRequest/job/{id}/professional/{professional}")
	void replaceJobProfessional(@PathVariable Long id, @PathVariable Long professional) throws JobNotFoundException {
		backendService.updateJobProfessional(id, professional);
	}
	//use case: delete client
	@DeleteMapping("/dataRequest/deljob/{id}")
	void deleteJob(@PathVariable Long id) {
		backendService.deleteJob(id);
	}
}