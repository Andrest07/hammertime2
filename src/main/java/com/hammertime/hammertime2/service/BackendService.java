package com.hammertime.hammertime2.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hammertime.hammertime2.database.CreateRating;
import com.hammertime.hammertime2.database.TempReview;
import com.hammertime.hammertime2.domain.client.Client;
import com.hammertime.hammertime2.domain.client.ClientRepository;
import com.hammertime.hammertime2.domain.images.Image;
import com.hammertime.hammertime2.domain.images.ImageRepository;
import com.hammertime.hammertime2.domain.job.Job;
import com.hammertime.hammertime2.domain.job.JobRepository;
import com.hammertime.hammertime2.domain.jobApplication.JobApplication;
import com.hammertime.hammertime2.domain.jobApplication.JobApplicationRepository;
import com.hammertime.hammertime2.domain.professional.Professional;
import com.hammertime.hammertime2.domain.professional.ProfessionalRepository;
import com.hammertime.hammertime2.domain.rating.Rating;
import com.hammertime.hammertime2.domain.rating.RatingRepository;
import com.hammertime.hammertime2.domain.transaction.Transaction;
import com.hammertime.hammertime2.domain.transaction.TransactionRepository;
import com.hammertime.hammertime2.exceptions.ClientNotFoundException;
import com.hammertime.hammertime2.exceptions.JobApplicationNotFoundException;
import com.hammertime.hammertime2.exceptions.JobNotFoundException;
import com.hammertime.hammertime2.exceptions.ProfessionalNotFoundException;
import com.hammertime.hammertime2.exceptions.RatingNotFoundException;

import net.datafaker.Faker;

@Service
public class BackendService implements IBackendService{
    @Autowired private final ClientRepository cRepository;
	@Autowired private final ProfessionalRepository pRepository;
	@Autowired private final RatingRepository rRepository;
	@Autowired private final JobRepository jRepository;
	@Autowired private final TransactionRepository tRepository;
	@Autowired private final JobApplicationRepository jAppRepository;
	@Autowired private final ImageRepository iRepository;

	private String temp;

	private IBackendService service;
	private final RestTemplate restTemplate;
	Logger log = LoggerFactory.getLogger(BackendService.class);
	Random random = new Random();
	Faker faker = new Faker();
	String firstName;
	String lastName;
	String businessName;
	String address;
	String phone;
	String email;
	String password;
	String lorem1;
	String lorem2;
	String jobCategory;
	Long clientId;
    
    BackendService(ClientRepository cRepository,
			ProfessionalRepository pRepository,
			RatingRepository rRepository, 
			JobRepository jRepository, 
			TransactionRepository tRepository,
			JobApplicationRepository jAppRepository, 
			ImageRepository iRepository,
			RestTemplateBuilder restTemplateBuilder){


		this.cRepository = cRepository;
		this.pRepository = pRepository;
		this.rRepository = rRepository;
		this.jRepository = jRepository;
		this.tRepository = tRepository;
		this.jAppRepository = jAppRepository;
		this.iRepository = iRepository;
		this.restTemplate = restTemplateBuilder.build();
	}

	public Long getRandomId(List<Long> list){
        Random random = new Random();
        Integer randMax = list.size() - 1;
        Long rand;
        rand = list.get(random.nextInt(randMax));
        return rand;
    }

	// +++++++++++++++++++++++ Client +++++++++++++++++++++++
	@Override
	public List<Client> getClients() {
		return cRepository.findAll();
	}
	@Override
	public List<Long> getClientIds() {
		List<Long> ids = new ArrayList<Long>();
		List<Client> clients = getClients();
		for(int i=0;i<clients.size();i++) {
			ids.add(clients.get(i).getId());
		}
		return ids;
	}
	@Override
	public Client createClient(Client c) throws ClientNotFoundException {
		log.info("Creating " + c.toString());
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		c.setPassword(encoder.encode(c.getPassword()));
		return cRepository.save(c);
	}
	@Override
	public Client updateClient(Client c, Long id) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return cRepository.findById(id)
		    .map(Client -> {
				Client.setFName(c.getFName());
		        Client.setLName(c.getLName());
		        Client.setAddress(c.getAddress());
		        Client.setPhone(c.getPhone());
				Client.setEmail(c.getEmail());
				Client.setPassword(encoder.encode(c.getPassword()));
				Client.setPassword(c.getPassword());
		        Client.setMemberEnd(c.getMemberEnd());
		    return cRepository.save(Client);
		    })
		    .orElseGet(() -> {
		        c.setId(id);
		        return cRepository.save(c);
		    });
	}
	@Override
	public Client getClient(Long id) throws ClientNotFoundException {
		try {
			//return repository.getReferenceById(id); This function lazy loads and causes errors, so changed to below
			return cRepository.findById(id).get();
			
		}catch(Exception e) {
			throw new ClientNotFoundException(id);
		}
	}
	@Override
	public void deleteClient(Long id) {
		cRepository.deleteById(id);
	}
	@Override
	public Client updateClientMemberEnd(Long id, Integer days) throws ClientNotFoundException {
		Client c;
		try {
			c = getClient(id);
		} catch (Exception e) {
			throw new ClientNotFoundException(id);
		}
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, +days);
		c.setMemberEnd(cal);
		return cRepository.findById(id)
		    .map(Client -> {
		        Client.setMemberEnd(c.getMemberEnd());
		    return cRepository.save(Client);
		    })
		    .orElseGet(() -> {
		        c.setId(id);
		        return cRepository.save(c);
		    });
	}
	@Override
	public void populateClient(Integer num) throws ClientNotFoundException {
		for (int i = 0; i < num; i++) {
			firstName = faker.name().firstName();
			lastName = faker.name().lastName();
			address = faker.address().fullAddress();
			phone = faker.phoneNumber().phoneNumber();
			email = faker.internet().emailAddress();
			password = faker.internet().password();
			createClient(new Client(firstName, lastName, address, phone, email, password));
		}
	}
	@Override
	public boolean verifyClient(String email, String password) throws ClientNotFoundException{
		try {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			Client c = cRepository.findByEmail(email);
			return encoder.matches(c.getPassword(), password);
		}catch(Exception e) {
			throw new ClientNotFoundException(email, password);
		}
	}

	// +++++++++++++++++++++++ Professional +++++++++++++++++++++++
	@Override
	public List<Professional> getProfessionals() {
		return pRepository.findAll();
	}

	@Override
	public List<Long> getProfessionalIds() {
		List<Long> ids = new ArrayList<Long>();
		List<Professional> professionals = getProfessionals();
		for(int i=0;i<professionals.size();i++) {
			ids.add(professionals.get(i).getId());
		}
		return ids;
	}

	@Override
	public Professional createProfessional(Professional p) {
		log.info("Creating " + p.toString());
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		p.setPassword(encoder.encode(p.getPassword()));
		return pRepository.save(p);
	}

	@Override
	public Professional updateProfessional(Professional p, Long id) {
		return pRepository.findById(id)
		      	.map(Professional -> {
		            Professional.setFName(p.getFName());
					Professional.setLName(p.getLName());
					Professional.setBusinessName(p.getBusinessName());
		            Professional.setAddress(p.getAddress());
		            Professional.setPhone(p.getPhone());
					Professional.setEmail(p.getEmail());
					Professional.setPassword(p.getPassword());
					Professional.setMemberEnd(p.getMemberEnd());
		        return pRepository.save(Professional);
		      })
		      	.orElseGet(() -> {
		        	p.setId(id);
		        	return pRepository.save(p);
		      });
	}

	@Override
	public Professional getProfessional(Long id) throws ProfessionalNotFoundException {
		try {
			//return repository.getReferenceById(id); This function lazy loads and causes errors, so changed to below
			return pRepository.findById(id).get();
			
		}catch(Exception e) {
			throw new ProfessionalNotFoundException(id);
		}
	}

	@Override
	public void deleteProfessional(Long id) {
		pRepository.deleteById(id);
	}

	@Override
	public Professional updateProfessionalMemberEnd(Long id, Integer days) throws ProfessionalNotFoundException {
		Professional p;
			try {
				p = getProfessional(id);
			} catch (Exception e) {
				throw new ProfessionalNotFoundException(id);
			}
			Calendar cal = Calendar.getInstance();
			if (p.getMemberEnd() == null || cal.before(p.getMemberEnd())) {
				cal.add(Calendar.DATE, +days);
				p.setMemberEnd(cal);
				return pRepository.findById(id)
					.map(Professional -> {
						Professional.setMemberEnd(p.getMemberEnd());
					return pRepository.save(Professional);
					})
					.orElseGet(() -> {
						p.setId(id);
						return pRepository.save(p);
					});
			} else {
				cal = p.getMemberEnd();
				cal.add(Calendar.DATE, +days);
				p.setMemberEnd(cal);
				return pRepository.findById(id)
					.map(Professional -> {
						Professional.setMemberEnd(p.getMemberEnd());
					return pRepository.save(Professional);
					})
					.orElseGet(() -> {
						p.setId(id);
						return pRepository.save(p);
					});
			}
	}
	@Override public void populateProfessional(Integer num) {
		for (int i = 0; i < num; i++) {
			firstName = faker.name().firstName();
			lastName = faker.name().lastName();
			businessName = faker.company().name();
			address = faker.address().fullAddress();
			phone = faker.phoneNumber().phoneNumber();
			email = faker.internet().emailAddress();
			password = faker.internet().password();
			log.info("Populating " + createProfessional(new Professional(firstName, lastName, businessName, address, phone, email, password)));
		}
	}
	@Override
	public boolean verifyProfessional(String email, String password) throws ProfessionalNotFoundException{
		try {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			Professional p = pRepository.findByEmail(email);
			return encoder.matches(p.getPassword(), password);
		}catch(Exception e) {
			throw new ProfessionalNotFoundException(email, password);
		}
	}

	// @Override
	// public Professional addProfessionalRating(Long pId, Long rId) throws ProfessionalNotFoundException {
	// 	Professional p = getProfessional(pId);
	// 	return pRepository.findById(p.getId())
	// 	      	.map(Professional -> {
	// 	            Professional.addRating(rId);
	// 	        return pRepository.save(Professional);
	// 	      	}).orElseGet(() -> {
	// 				p.setId(pId);
	// 				return pRepository.save(p);
	// 			});
	// }

	// +++++++++++++++++++++++ Ratings +++++++++++++++++++++++
	@Override
	public List<Rating> getRatings() {
		return rRepository.findAll();
	}

	@Override
	public List<Long> getRatingIds() {
		List<Long> ids = new ArrayList<Long>();
		List<Rating> professionals = getRatings();
		for(int i=0;i<professionals.size();i++) {
			ids.add(professionals.get(i).getId());
		}
		return ids;
	}

	@Override
	public Rating createRating(Rating r) throws ProfessionalNotFoundException {
		// try {
		return rRepository.save(r);
		// } finally {
		// 	addProfessionalRating(r.getProfessionalId(), r.getId());
		// }
	}

	@Override
	public Rating updateRating(Rating r, Long id) {
		return rRepository.findById(id)
		      	.map(Rating -> {
		            Rating.setClientId(r.getClientId());
					Rating.setProfessionalId(r.getProfessionalId());
		            Rating.setRating(r.getRating());
		            Rating.setReviewTitle(r.getReviewTitle());
					Rating.setReviewDescription(r.getReviewDescription());
		        return rRepository.save(Rating);
		      })
		      	.orElseGet(() -> {
		        	r.setId(id);
		        	return rRepository.save(r);
		      });
	}

	@Override
	public Rating getRating(Long id) throws RatingNotFoundException {
		try {
			//return repository.getReferenceById(id); This function lazy loads and causes errors, so changed to below
			return rRepository.findById(id).get();
			
		}catch(Exception e) {
			throw new RatingNotFoundException(id);
		}
	}

	@Override
	public void deleteRating(Long id) {
		rRepository.deleteById(id);
	}

	@Override
	public void populateRating() {
		List<Long> proIds = getProfessionalIds();
            List<Long> jobIds = getJobIds();
            Integer randMax = proIds.size() - 1;
            for (Long x : jobIds) {
                Integer randPro = random.nextInt(randMax);
                Integer randChoice = random.nextInt(3);

                if(randChoice == 1){
                    updateJobProfessional(x, proIds.get(randPro));
					log.info("Preloading " + getJob(x));
                } else if(randChoice == 2){
                    updateJobProfessional(x, proIds.get(randPro));
                    updateJobStatus(x, "COMPLETE");
					log.info("Preloading " + getJob(x));

                    lorem1 = faker.lorem().sentence(3, 2);
                    CreateRating cr = new CreateRating();
                    TempReview tr = cr.getRandomRev();
                    log.info("Preloading " + rRepository.save(new Rating(getJob(x).getClientId(), proIds.get(randPro), tr.getRating(), lorem1, tr.getDescription())));
                    
                }
            }
	}

	// +++++++++++++++++++++++ Job +++++++++++++++++++++++
	@Override
	public Job createJob(Job job) {
		return jRepository.save(job);
	}
	@Override
	public void deleteJob(Long id) {
		jRepository.deleteById(id);
	}
	@Override
	public List<Job> getJobs() {
		return jRepository.findAll();
	}
	@Override
	public List<Long> getJobIds() {
		List<Long> ids = new ArrayList<Long>();
		List<Job> jobs = getJobs();

		for(int i=0;i<jobs.size();i++) {
			ids.add(jobs.get(i).getId());
		}
		return ids;
	}
	@Override
	public Job getJob(Long id){
		List<Job> jobs = getJobs();
		Job foundJob = null;

		for(int i = 0; i < jobs.size(); i++){
			if(jobs.get(i).getId() == id){
				foundJob = jobs.get(i);
				break;
			}
		}

		return foundJob;
	}
	@Override
	public List<Job> getClientJobs(Long clientID){
		List<Job> jobs = getJobs();
		List<Job> clientJobs = new ArrayList<Job>();

		for(int i = 0; i < jobs.size(); i++){
			if(jobs.get(i).getClientId() == clientID){
				clientJobs.add(jobs.get(i));
			}
		}
		return clientJobs;
	}
	@Override
	public List<Job> getProfessionalJobs(Long professionalId){
		List<Job> jobs = getJobs();
		List<Job> professionalJobs = new ArrayList<Job>();

		for(int i = 0; i < jobs.size(); i++){
			if(jobs.get(i).getProfessionalId() == professionalId){
				professionalJobs.add(jobs.get(i));
			}
		}
		return professionalJobs;
	}
	@Override
	public void setJobTitle(Long id, String jobTitle){
		getJob(id).setTitle(jobTitle);
	}
	@Override
	public void setJobCategory(Long id, String jobCategory){
		if(Job.JobCategory.contains(jobCategory)){
			getJob(id).setCategory(Job.JobCategory.valueOf(jobCategory));
		}
	}
	@Override
	public void setJobDescription(Long id, String jobDescription){
		getJob(id).setDescription(jobDescription);
	}
	@Override
	public void updateJobStatus(Long id, String jobStatus){
		Job job = getJob(id);
		// Checks if the entered job status is within the enum
		if(Job.JobStatus.contains(jobStatus)){
			//System.out.println(jobStatus)
			job.setStatus(Job.JobStatus.valueOf(jobStatus));
			jRepository.save(job);
		}
	}
	@Override
	public void updateJobProfessional(Long id, Long professionalId){
		Job job = getJob(id);
		if(job.getProfessionalId() == null){
			job.setStatus(Job.JobStatus.ACCEPTED);
		}
		job.setProfessionalId(professionalId);
		jRepository.save(job);
		//getJob(id).setProfessionalId(proffessionalId);
	}
	@Override
	public void updateJobJobApplication(Long id, Long jobApplication){
		Job job = getJob(id);
		job.addJobApplication(jobApplication);
		jRepository.save(job);
		//getJob(id).setProfessionalId(proffessionalId);
	}
	@Override public void populateJob(Integer num) {
		for (int i = 0; i < num; i++) {
			lorem1 = faker.lorem().sentence(5, 5);
			List<Job.JobCategory> VALUES = Collections.unmodifiableList(Arrays.asList(Job.JobCategory.values()));
			int SIZE = VALUES.size();
			jobCategory = VALUES.get(random.nextInt(SIZE)).toString();
			lorem2 = faker.lorem().paragraph(3);
			clientId = getRandomId(getClientIds());
			log.info("Populating " + createJob(new Job(lorem1, jobCategory, lorem2, clientId)));
		}
	}

	// +++++++++++++++++++++++ Transaction +++++++++++++++++++++++
	@Override
	public Transaction createTransaction(Transaction transaction) {
		return tRepository.save(transaction);
	}
	@Override
	public void deleteTransaction(Long id){
		tRepository.deleteById(id);
	}
	@Override
	public List<Transaction> getTransactions() {
		return tRepository.findAll();
	}
	@Override
	public List<Long> getTransactionIds() {
		List<Long> ids = new ArrayList<Long>();
		List<Transaction> transactions = getTransactions();
		for(int i=0;i<transactions.size();i++) {
			ids.add(transactions.get(i).getTransactionId());
		}
		return ids;
	}
	public Transaction getTransaction(Long id){
		List<Transaction> transactions = getTransactions();
		Transaction t = null;

		for(int i = 0; i < transactions.size(); i++){
			if (transactions.get(i).getTransactionId() == id){
				t = transactions.get(i);
			}
		}


		return t;
	}

	//--------------------------------Job applications--------------------------------
	//for professional table to add job application
	// @Override
	// public Professional addProfessionalJobApplication(Long pId, Long jaId) throws ProfessionalNotFoundException {
	// 	Professional p = getProfessional(pId);
		
	// 	return pRepository.findById(p.getId())
	// 	      	.map(Professional -> {
	// 	            Professional.addJobApplication(jaId);
	// 	        return pRepository.save(Professional);
	// 	      	}).orElseGet(() -> {
	// 				p.setId(pId);
	// 				return pRepository.save(p);
	// 			});
	// }

	//for job table to add job application
	@Override
	public Job addJobApplicationToJob(Long jId, Long jaId) throws JobNotFoundException {
		Job j = getJob(jId);
		
		return jRepository.findById(j.getId())
		      	.map(Job -> {
		            Job.addJobApplication(jaId);
		        return jRepository.save(Job);
		      	}).orElseGet(() -> {
					j.setId(jId);
					return jRepository.save(j);
				});
	}


	@Override
	public List<JobApplication> getJobApplications() {
		return jAppRepository.findAll();
	}
	
	@Override
	public List<Long> getJobApplicationIds() {
		List<Long> ids = new ArrayList<Long>();
		List<JobApplication> jobApplications = getJobApplications();
		
		for(int i=0;i<jobApplications.size();i++) {
			ids.add(jobApplications.get(i).getId());
		}
		return ids;
	}

	@Override
	public JobApplication getJobApplication(Long id) throws JobApplicationNotFoundException {
		try {
			//return repository.getReferenceById(id); This function lazy loads and causes errors, so changed to below
			return jAppRepository.findById(id).get();
			
		}catch(Exception e) {
			throw new JobApplicationNotFoundException(id);
		}
	}

	@Override
	public JobApplication createJobApplication(JobApplication ja) throws ProfessionalNotFoundException, JobNotFoundException {
		try {
		//log.info("ja: " + ja);
		return jAppRepository.save(ja);
		} finally {
			//log.info("ja: " + ja.getProfessionalId());
			//log.info("ja: " + ja.getId());
			//log.info("ja: " + ja.getJobId());
			// addProfessionalJobApplication(ja.getProfessionalId(), ja.getId());
			addJobApplicationToJob(ja.getJobId(), ja.getId());
		 }
	}

	@Override
	public JobApplication updateJobApplication(JobApplication ja, Long id) {
		return jAppRepository.findById(id)
		      	.map(JobApplication -> {
		            JobApplication.setProfessionalId(ja.getProfessionalId());
					JobApplication.setJobId(ja.getJobId());
		            JobApplication.setQuotedAmount(ja.getQuotedAmount());
		        return jAppRepository.save(JobApplication);
		      }).orElseGet(() -> {
				ja.setId(id);
				return jAppRepository.save(ja);
			  });
	}

	@Override
	public void deleteJobApplication(Long id) {
		jAppRepository.deleteById(id);
	}

	@Override
	public void populateAll(Integer num) throws ClientNotFoundException {
		populateClient(num);
		populateProfessional(num);
		populateJob(num);
		populateRating();
	}

	// +++++++++++++++++++++++ Images +++++++++++++++++++++++
	@Override
	public Image createImage(Image image) {
		return iRepository.save(image);
	}
	@Override
	public void deleteImage(Long id){
		tRepository.deleteById(id);
	}
	@Override
	public List<Image> getAllImage() {
		return iRepository.findAll();
	}
	@Override
	public List<Long> getImageIds() {
		List<Long> ids = new ArrayList<Long>();
		List<Image> images = getAllImage();
		for(int i=0;i<images.size();i++) {
			ids.add(images.get(i).getImageId());
		}
		return ids;
	}
	public Image getImage(Long id){
		List<Image> images = getAllImage();
		Image i = null;
		for(int j = 0; j < images.size(); j++){
			if (images.get(j).getImageId() == id){
				i = images.get(j);
			}
		}
		return i;
	}
}
