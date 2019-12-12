package com.example.springsocial.repository;

import com.example.springsocial.model.Employee;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
        
    void deleteByUserId(Long userId);
    
    public static final String UPDATE_EMPLOYEE_BY_USER_ID = "UPDATE employees SET title=? WHERE user_id = ?;";
    
    @Transactional
    @Modifying
    @Query(value = UPDATE_EMPLOYEE_BY_USER_ID, nativeQuery = true)
    public void updateEmployeeTitle(String title, Long userId);

    public Optional<Employee> findByEmployeeEmail(String email);

}
