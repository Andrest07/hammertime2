package com.hammertime.hammertime2.domain.userTem;

import java.util.Calendar;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class UserTem {
    private @Id @GeneratedValue Long id;
    String firstName;
    String lastName;
    String address;
    String phone;
    String email;
    String password;
    Calendar memberEnd;

    public UserTem(){}
    public UserTem(String firstName, String lastName, String address, String phone, String email, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.password = password;
        Calendar cal = Calendar.getInstance();
        cal.set(2000, 1, 1);
        this.memberEnd = cal;
    }
    // Accessors
    public String getFName(){
        return firstName;
    }
    public String getLName(){
        return lastName;
    }
    public String getAddress(){
        return address;
    }
    public String getPhone(){
        return phone;
    }
    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }
    public Calendar getMemberEnd(){
        return memberEnd;
    }
    public Long getId(){
        return id;
    }
    // Mutators
    public void setFName(String firstName){
        this.firstName = firstName;
    }
    public void setLName(String lastName){
        this.lastName = lastName;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
    public void setEmail(String email) {
    	this.email = email;
    }
    public void setPassword(String password) {
    	this.password = password;
    }
    public void setMemberEnd(Calendar memberEnd) {
    	this.memberEnd = memberEnd;
    }
    public void setId(Long id){
        this.id = id;
    }
   
    public boolean equals(UserTem user) {
        if (this == user)
            return true;
        if (!(user instanceof UserTem))
            return false;
            UserTem client = (UserTem) user;
        return Objects.equals(this.id, client.id) 
        && Objects.equals(this.firstName, client.firstName)
        && Objects.equals(this.lastName, client.lastName)
        && Objects.equals(this.address, client.address)
        && Objects.equals(this.phone, client.phone)
        && Objects.equals(this.email, client.email)
        && Objects.equals(this.password, client.password)
        && Objects.equals(this.memberEnd, client.memberEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.firstName, this.lastName, this.address, this.phone, this.email, this.password, this.memberEnd);
    }

    @Override
    public String toString() {
        return "Client{" + "id=" + this.id + "\'"
        + ", firstName='" + this.firstName + "\'"
        + ", lastName='" + this.lastName + "\'"
        + ", address='" + this.address + "\'"
        + ", phone='" + this.phone + "\'"
        + ", email='" + this.email + "\'"
        + ", password='" + this.password + "\'"
        + ", memberEnd='" + this.memberEnd.getTime() + "\'"
        + "}";
    }
}
