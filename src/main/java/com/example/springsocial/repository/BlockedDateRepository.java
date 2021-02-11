package com.example.springsocial.repository;

import com.example.springsocial.model.BlockedDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface BlockedDateRepository  extends JpaRepository<BlockedDate, Long> {

    public static final String DELETE_BLOCKED_DATE = "DELETE FROM company_blocked_dates WHERE company_id = ? AND blocked_date = ?;";

    @Transactional
    @Modifying
    @Query(value = DELETE_BLOCKED_DATE, nativeQuery = true)   
    public void deleteByCompanyIdAndDate(Long companyId, String date);

    
    public static final String ADD_BLOCKED_DATE = "INSERT INTO company_blocked_dates (company_id, blocked_date) VALUES (?, ?);";

    @Transactional
    @Modifying
    @Query(value = ADD_BLOCKED_DATE, nativeQuery = true)   
    public void addByCompanyIdAndDate(Long companyId, String date);

    
}
