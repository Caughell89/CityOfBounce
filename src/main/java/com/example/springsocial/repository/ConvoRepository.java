package com.example.springsocial.repository;

import com.example.springsocial.model.Convo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ConvoRepository extends JpaRepository<Convo, Long> {
    
    
    public static final String SELECT_CONVOS_BY_USER_ID = "SELECT * FROM convo_users WHERE user_id = ?;";
    
  
    @Query(value = SELECT_CONVOS_BY_USER_ID, nativeQuery = true)
    public List<Convo> findUserConvoByFkUser(Long userId);
}
