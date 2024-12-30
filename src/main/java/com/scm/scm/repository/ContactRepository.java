package com.scm.scm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.scm.scm.entity.Contact;
import com.scm.scm.entity.User;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String> {

    List<Contact> findByUser(User user);

    @Query("SELECT c FROM Contact c WHERE c.user.id = :userId")
    List<Contact> findByUserId(@Param("userId") String userId);
}
