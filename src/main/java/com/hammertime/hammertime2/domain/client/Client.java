package com.hammertime.hammertime2.domain.client;



import com.hammertime.hammertime2.domain.userTem.UserTem;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
//@Table(name="Client")
@Table(name = "USER_TEM")
public class Client extends UserTem{
    public Client(){}
    public Client(String firstName, String lastName, String address, String phone, String email, String password){
        super(firstName, lastName, address, phone, email, password);
    }
}

