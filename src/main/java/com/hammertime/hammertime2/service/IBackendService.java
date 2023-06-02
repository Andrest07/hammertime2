package com.hammertime.hammertime2.service;

import java.util.List;

import com.hammertime.hammertime2.domain.client.Client;
import com.hammertime.hammertime2.domain.images.Image;
import com.hammertime.hammertime2.domain.job.Job;
import com.hammertime.hammertime2.domain.jobApplication.JobApplication;
import com.hammertime.hammertime2.domain.professional.Professional;
import com.hammertime.hammertime2.domain.rating.Rating;
import com.hammertime.hammertime2.domain.transaction.Transaction;
import com.hammertime.hammertime2.exceptions.ClientNotFoundException;
import com.hammertime.hammertime2.exceptions.JobApplicationNotFoundException;
import com.hammertime.hammertime2.exceptions.JobNotFoundException;
import com.hammertime.hammertime2.exceptions.ProfessionalNotFoundException;
import com.hammertime.hammertime2.exceptions.RatingNotFoundException;

public interface IBackendService {
	public abstract List<Client> getClients();
	public abstract List<Long> getClientIds();
	public abstract Client createClient(Client c) throws ClientNotFoundException;
	public abstract Client updateClient(Client c, Long id);
	public abstract void deleteClient(Long id);
	public abstract Client getClient(Long id) throws ClientNotFoundException;
	public abstract Client updateClientMemberEnd(Long id, Integer days) throws ClientNotFoundException;
	public abstract void populateClient(Integer num) throws ClientNotFoundException;
	public abstract boolean verifyClient(String email, String password) throws ClientNotFoundException;
	
	public abstract List<Professional> getProfessionals();
	public abstract List<Long> getProfessionalIds();
	public abstract Professional createProfessional(Professional p);
	public abstract Professional updateProfessional(Professional p, Long id);
	public abstract void deleteProfessional(Long id);
	public abstract Professional getProfessional(Long id) throws ProfessionalNotFoundException;
	public abstract Professional updateProfessionalMemberEnd(Long id, Integer days) throws ProfessionalNotFoundException;
	public abstract void populateProfessional(Integer num);
	public abstract boolean verifyProfessional(String email, String password) throws ProfessionalNotFoundException;
	//public abstract Professional addProfessionalRating(Long pId, Long rId) throws ProfessionalNotFoundException;

	public abstract List<Rating> getRatings();
	public abstract List<Long> getRatingIds();
	public abstract Rating createRating(Rating r) throws ProfessionalNotFoundException;
	public abstract Rating updateRating(Rating r, Long id);
	public abstract void deleteRating(Long id);
	public abstract Rating getRating(Long id) throws RatingNotFoundException;
	public abstract void populateRating();

	// Jobs
	public abstract Job createJob(Job job);
	public abstract void deleteJob(Long id);
	public abstract List<Job> getJobs();
	public abstract List<Long> getJobIds();
	public abstract Job getJob(Long id);
	public abstract List<Job> getClientJobs(Long clientID);
	public abstract List<Job> getProfessionalJobs(Long professionalId);
	public abstract void setJobTitle(Long id, String jobTitle);
	public abstract void setJobCategory(Long id, String jobCategory);
	public abstract void setJobDescription(Long id, String jobDescription); 
	public abstract void updateJobStatus(Long id, String jobStatus);
	public abstract void updateJobProfessional(Long id, Long proffessionalId);
	public abstract void updateJobJobApplication(Long id, Long applicationId);
	public abstract void populateJob(Integer num);


	// Transactions
	public abstract Transaction createTransaction(Transaction transaction);
	public abstract void deleteTransaction(Long id);
	public abstract List<Transaction> getTransactions();
	public abstract List<Long> getTransactionIds();
	public abstract Transaction getTransaction(Long id);

	//Job Application
	// public abstract Professional addProfessionalJobApplication(Long pId, Long jaId) throws ProfessionalNotFoundException;
	public abstract Job addJobApplicationToJob(Long jId, Long jaId) throws JobNotFoundException;
	public abstract List<JobApplication> getJobApplications(); 
	public abstract List<Long> getJobApplicationIds();
	public abstract JobApplication getJobApplication(Long id) throws JobApplicationNotFoundException;
	public abstract JobApplication createJobApplication(JobApplication ja) throws ProfessionalNotFoundException, JobNotFoundException;
	public abstract JobApplication updateJobApplication(JobApplication ja, Long id);
	public abstract void deleteJobApplication(Long id);
	public abstract void populateAll(Integer num) throws ClientNotFoundException;

	// Images
	public abstract Image createImage(Image image);
	public abstract void deleteImage(Long id);
	public abstract List<Image> getAllImage();
	public abstract List<Long> getImageIds();
	public abstract Image getImage(Long id);
}
