package com.example.springsocial.repository;

import com.example.springsocial.model.CompanyMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface MessageRepository extends JpaRepository<CompanyMessage, Long> {

    public static final String MARK_AS_READ = "UPDATE company_messages SET is_read = 1, read_at = NOW() WHERE message_id = ?;";
    
    @Transactional
    @Modifying
    @Query(value = MARK_AS_READ, nativeQuery = true)
    public void setIsRead(Long messageId);
    
    public static final String MARK_AS_DELETED = "UPDATE company_messages SET is_deleted = 1, deleted_at = NOW() WHERE message_id = ?;";
    
    @Transactional
    @Modifying
    @Query(value = MARK_AS_DELETED, nativeQuery = true)
    public void setIsDeleted(Long messageId);

    public static final String DELETE_OLD_MESSAGES = "DELETE FROM company_messages WHERE deleted_at < NOW() - INTERVAL 30 DAY;";
    
    @Transactional
    @Modifying
    @Query(value = DELETE_OLD_MESSAGES, nativeQuery = true)
    public void deleteOldMessages();

     public static final String MARK_AS_NOT_DELETED = "UPDATE company_messages SET is_deleted = 0, deleted_at = null WHERE message_id = ?;";
    
    @Transactional
    @Modifying
    @Query(value = MARK_AS_NOT_DELETED, nativeQuery = true)
    public void setNotDeleted(Long messageId);
    
}
