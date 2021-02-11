package com.example.springsocial.repository;

import com.example.springsocial.model.CompanyMessage;
import com.example.springsocial.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface MessageRepository extends JpaRepository<Message, Long> {

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

    public static final String SET_CONVO_INFO = "UPDATE convo_users SET last_viewed = NOW() WHERE user_id= ? AND  convo_id = ?;";

    @Transactional
    @Modifying
    @Query(value = SET_CONVO_INFO, nativeQuery = true)
    public void setConvoInfo(Long userId, Long convoId);
    
}
