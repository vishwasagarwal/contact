package com.cms.demo.respository;

import com.cms.demo.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepo extends JpaRepository<Contact, Long> {

    List<Contact> findByFirstName(String firstName);

    List<Contact> findByLastName(String lastName);

    List<Contact> findByEmail(String email);
}
