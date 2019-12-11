package com.example.springsocial.repository;

import com.example.springsocial.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByEmail(String email);
    
    Boolean existsByEmail(String email);
    
    
    public static final String CLEAR_USERS_COMPANY = "UPDATE users SET company_id = null WHERE email = ?;";

    @Transactional
    @Modifying
    @Query(value = CLEAR_USERS_COMPANY, nativeQuery = true)
    public void clearCompany(@Param("email") String email);

    
    
}
