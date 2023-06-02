// Package
package com.hammertime.hammertime2.domain.job;

// java
import java.util.Objects;
import java.util.*;

import jakarta.persistence.Column;
// jakarta
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Job")
public class Job {
    private @Id @GeneratedValue Long id;
    String jobTitle;
    public enum JobCategory{
        CARPENTRY,
        ELECTRICAL,
        PLUMBING,
        HVAC,
        PAINTING,
        ROOFING,
        MASONRY,
        FLOORING,
        LANDSCAPING,
        CONTRACTING,
        OTHER;

        public static boolean contains(String jobCategories){
            for (JobCategory enumVals : values()) {
                if (enumVals.name().equalsIgnoreCase(jobCategories)) {
                    return true;
                }
            }
            return false;
        }
    }
    JobCategory jobCategory;
    
    @Column(length = 1024)
    String jobDescription;
    public enum JobStatus{
        PENDING,
        ACCEPTED,
        COMPLETE;

        public static boolean contains(String jobStatus){
            for (JobStatus enumVals : values()) {
                if (enumVals.name().equalsIgnoreCase(jobStatus)) {
                    return true;
                }
            }
            return false;
        }
    }
    JobStatus jobStatus;
    Long clientID;
    Long professionalID;
    List<Long> jobApplicationIds;


    Job () {}

    public Job(String jobTitle, String jobCategory, String jobDescription, Long clientID){
        this.jobTitle = jobTitle;
        this.jobCategory = JobCategory.valueOf(jobCategory);
        this.jobDescription = jobDescription;
        this.jobStatus = JobStatus.PENDING;
        this.clientID = clientID;
        this.professionalID = null;
        this.jobApplicationIds = new ArrayList<>();
    }

    // Accessors
    public Long getId(){ return id;}
    public String getTitle(){ return jobTitle;}
    public JobCategory getCategory(){ return jobCategory;}
    public String getDescription(){ return jobDescription;}
    public JobStatus getStatus(){ 
        return jobStatus;
    }
    public Long getClientId(){ return clientID;}
    public Long getProfessionalId(){ return professionalID;}
    
    public List<Long> getJobApplication(){
        return jobApplicationIds;
    }

    // Mutators
    public void setTitle(String jobTitle){ this.jobTitle = jobTitle; }
    public void setId(Long id){ this.id = id; }
    public void setCategory(JobCategory jobCategory){ this.jobCategory = jobCategory; }
    public void setDescription(String jobDescription){ this.jobDescription = jobDescription; }
    public void setStatus(JobStatus jobStatus){ this.jobStatus = jobStatus; }
    public void setProfessionalId(Long professionalID){
        this.professionalID = professionalID;
    }

    public void setJobApplication(List<Long> newJobApplicationIds){
        this.jobApplicationIds = newJobApplicationIds;
    }

    public void addJobApplication(Long newJobApplicationId){
        this.jobApplicationIds.add(newJobApplicationId);
    }

    // Check if job is the same as another job
    public boolean equals(Job o) {
        if (this == o)
            return true;
        if (!(o instanceof Job))
            return false;
            Job job = (Job) o;
        return Objects.equals(this.id, job.id) 
        && Objects.equals(this.jobTitle, job.jobTitle)
        && Objects.equals(this.jobCategory, job.jobCategory)
        && Objects.equals(this.jobDescription, job.jobDescription)
        && Objects.equals(this.jobStatus, job.jobStatus)
        && Objects.equals(this.clientID, job.clientID)
        && Objects.equals(this.professionalID, job.professionalID)
        && Objects.equals(this.jobApplicationIds, job.jobApplicationIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.jobTitle, this.jobCategory, this.jobDescription, this.jobStatus, this.clientID, this.professionalID, this.jobApplicationIds);
    }

    @Override
    public String toString() {
        return "Job{" + "id=" + this.id + "\'"
        + ", jobTitle='" + this.jobTitle + "\'"
        + ", jobCategory='" + this.jobCategory + "\'"
        + ", jobDescription='" + this.jobDescription + "\'"
        + ", jobStatus='" + this.jobStatus.toString() + "\'"
        + ", clientID='" + this.clientID + "\'"
        + ", professionalID='" + this.professionalID + "\'"
        + ", job application='" + this.jobApplicationIds + "\'"
        + "}";
    }
}
