package com.hammertime.hammertime2.domain.rating;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Rating {
    private @Id @GeneratedValue Long id;
    Long clientId;
    Long professionalId;
    double rating;
    String reviewTitle;
    String reviewDescription;

    Rating () {}

    public Rating(Long cId, Long pId, double r, String rt, String rd){
        clientId = cId;
        professionalId = pId;
        rating = r;
        reviewTitle = rt;
        reviewDescription = rd;
    }

    public Long getId(){
        return id;
    }

    public Long getClientId(){
        return clientId;
    }

    public Long getProfessionalId(){
        return professionalId;
    }


    public double getRating(){
        return rating;
    }

    public String getReviewTitle(){
        return reviewTitle;
    }

    public String getReviewDescription(){
        return reviewDescription;
    }
    
    public void setId(Long id){
        this.id = id;
    }

    public void setClientId(Long newClientId){
        this.clientId = newClientId;
    }

    public void setProfessionalId(Long newProfessionalId){
        this.professionalId = newProfessionalId;
    }

    public void setRating(double newRating){
        this.rating = newRating;
    }

    public void setReviewTitle(String newReviewTitle){
    	this.reviewTitle = newReviewTitle;
    }

    public void setReviewDescription(String newReviewDescription){
    	this.reviewDescription = newReviewDescription;
    }

    public boolean equals(Rating o){
        if (this == o)
            return true;
        if (!(o instanceof Rating))
            return false;
            Rating rating = (Rating) o;
        return Objects.equals(this.id, rating.id)
        && Objects.equals(this.clientId, rating.clientId)
        && Objects.equals(this.professionalId, rating.professionalId)
        && Objects.equals(this.rating, rating.rating)
        && Objects.equals(this.reviewTitle, rating.reviewTitle)
        && Objects.equals(this.reviewDescription, rating.reviewDescription);
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.id, this.clientId, this.professionalId, this.rating, this.reviewTitle, this.reviewDescription);
    }

    @Override
    public String toString(){
        return "Rating{" + "id=" + this.id + "\'"
        + ", client='" + this.clientId + "\'"
        + ", professional='" + this.professionalId + "\'"
        + ", rating='" + this.rating + "\'"
        + ", reviewTitle='" + this.reviewTitle + "\'"
        + ", reviewDescription='" + this.reviewDescription + "\'"
        + "}";
    }
}
