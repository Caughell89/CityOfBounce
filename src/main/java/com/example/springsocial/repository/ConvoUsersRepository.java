package com.example.springsocial.repository;

import com.example.springsocial.model.ConvoUser;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConvoUsersRepository extends JpaRepository<ConvoUser, Long>  {

    public List<ConvoUser> findByUserId(Long userId);
    
}
