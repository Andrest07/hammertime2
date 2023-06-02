package com.hammertime.hammertime2.domain.professional;

import java.util.Objects;

import com.hammertime.hammertime2.domain.userTem.UserTem;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
//@Table(name="Professional")
@Table(name = "USER_TEM")
public class Professional extends UserTem{
    String businessName;

    public Professional(){}
    public Professional(String firstName, String lastName, String businessName, String address, String phone, String email, String password){
        super(firstName, lastName, address, phone, email, password);
        this.businessName = businessName;
    }

    // Mutators
    public void setBusinessName(String businessName){
        this.businessName = businessName;
    }

    // Accessors
    public String getBusinessName(){
        return businessName;
    }

    @Override
    public boolean equals(UserTem user) {
        if (this == user)
            return true;
        if (!(user instanceof UserTem))
            return false;
            Professional professional = (Professional) user;
        return Objects.equals(this.getId(), professional.getId()) 
        && Objects.equals(this.getFName(), professional.getFName())
        && Objects.equals(this.getLName(), professional.getLName())
        && Objects.equals(this.getAddress(), professional.getAddress())
        && Objects.equals(this.getPhone(), professional.getPhone())
        && Objects.equals(this.getEmail(), professional.getEmail())
        && Objects.equals(this.getPassword(), professional.getPassword())
        && Objects.equals(this.getMemberEnd(), professional.getMemberEnd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), this.getFName(), this.getLName(), this.getAddress(), this.getPhone(), this.getEmail(), this.getPassword(), this.getMemberEnd(), this.businessName);
    }

    @Override
    public String toString() {
        return "Professional{" + "id=" + this.getId() + "\'"
        + ", firstName='" + this.getFName() + "\'"
        + ", lastName='" + this.getLName() + "\'"
        + ", address='" + this.getAddress() + "\'"
        + ", phone='" + this.getPhone() + "\'"
        + ", email='" + this.getEmail() + "\'"
        + ", password='" + this.getPassword() + "\'"
        + ", memberEnd='" + this.getMemberEnd().getTime() + "\'"
        + ", businessName='" + this.businessName + "\'"
        + "}";
    }
}