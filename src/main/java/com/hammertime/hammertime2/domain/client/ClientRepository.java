package com.hammertime.hammertime2.domain.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import jakarta.persistence.QueryHint;


public interface ClientRepository extends JpaRepository<Client, Long>{
    @Query(value = "SELECT * FROM USER_TEM WHERE EMAIL=:email AND DTYPE='Client'",nativeQuery = true)
    @QueryHints(@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_CACHEABLE, value = "true"))
    public Client findByEmail(@Param("email")String email);
}