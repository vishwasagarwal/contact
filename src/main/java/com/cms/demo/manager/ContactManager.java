package com.cms.demo.manager;

import com.cms.demo.dto.ContactDto;
import com.cms.demo.entity.Contact;
import com.cms.demo.mapper.ContactMapper;
import com.cms.demo.respository.ContactRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class ContactManager {

    @Autowired
    private ContactRepo repository;

    public ContactDto save(ContactDto dto) {
        return ContactMapper.INSTANCE.entityToDto(repository.save(ContactMapper.INSTANCE.dtoToEntity(dto)));
    }

    public ContactDto findById(Long id) {
        return ContactMapper.INSTANCE.entityToDto(repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Contact not found")));
    }

    public void delete(Long id) {
        Contact contact = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Contact not found"));
        repository.delete(contact);
    }

    public List<ContactDto> findByEmail(String email) {
        return ContactMapper.INSTANCE.entitiesToDtoList(repository.findByEmail(email));
    }

    public List<ContactDto> findByLastName(String lastName) {
        return ContactMapper.INSTANCE.entitiesToDtoList(repository.findByLastName(lastName));
    }

    public List<ContactDto> findByFirstName(String firstName) {
        return ContactMapper.INSTANCE.entitiesToDtoList(repository.findByFirstName(firstName));
    }
}
