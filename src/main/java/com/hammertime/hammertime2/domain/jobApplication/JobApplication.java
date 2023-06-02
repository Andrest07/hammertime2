package com.hammertime.hammertime2.domain.jobApplication;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class JobApplication{
    private @Id @GeneratedValue Long id;
    Long proId;
    Long jobId;
    double quotedAmount;

    JobApplication(){}

    public JobApplication(Long pId, Long jId, double qA){
        proId = pId;
        jobId = jId;
        quotedAmount = qA;
    }

    public Long getId(){
        return id;
    }

    public Long getJobId(){
        return jobId;
    }

    public Long getProfessionalId(){
        return proId;
    }

    public double getQuotedAmount(){
        return quotedAmount;
    }
    
    public void setId(Long id){
        this.id = id;
    }

    public void setJobId(Long newJobId){
        this.jobId = newJobId;
    }

    public void setProfessionalId(Long newProfessionalId){
        this.proId = newProfessionalId;
    }

    public void setQuotedAmount(double newQuotedAmount){
        this.quotedAmount = newQuotedAmount;
    }

    public boolean equals(JobApplication o){
        if (this == o)
            return true;
        if (!(o instanceof JobApplication))
            return false;
            JobApplication jobApplication = (JobApplication) o;
        return Objects.equals(this.id, jobApplication.id)
        && Objects.equals(this.proId, jobApplication.proId)
        && Objects.equals(this.jobId, jobApplication.jobId)
        && Objects.equals(this.quotedAmount, jobApplication.quotedAmount);
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.id, this.proId, this.jobId, this.quotedAmount);
    }

    @Override
    public String toString(){
        return "Job Application{" + "id=" + this.id + "\'"
        + ", job='" + this.jobId + "\'"
        + ", professional='" + this.proId + "\'"
        + ", quoted amount='" + this.quotedAmount + "\'"
        + "}";
    }

}