package com.hammertime.hammertime2.domain.professional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import jakarta.persistence.QueryHint;


public interface ProfessionalRepository extends JpaRepository<Professional, Long>{
    @Query(value = "SELECT * FROM USER_TEM WHERE EMAIL=:email AND DTYPE='Professional'",nativeQuery = true)
    @QueryHints(@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_CACHEABLE, value = "true"))
    public Professional findByEmail(@Param("email")String email);
}