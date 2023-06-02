// package com.hammertime.backend.domain.userTem;

// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;

// public interface UserTemController extends JpaRepository<UserTem, Long>{
//     @Query(value = "SELECT * FROM USER_TEM WHERE EMAIL=:email AND DTYPE='Client'",nativeQuery = true)
//     public UserTem findByEmail(@Param("email")String email);
// }