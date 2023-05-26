package com.cms.demo.service;

import com.cms.demo.dto.ContactDto;
import com.cms.demo.manager.ContactManager;
import com.cms.demo.mapper.ContactMapper;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactManager manager;

    public ContactDto save(ContactDto dto) {
        return manager.save(dto);
    }

    public ContactDto update(long id, ContactDto updateDto) {
        ContactDto existingDto = manager.findById(id);
        if (ObjectUtils.isEmpty(existingDto)) {
            throw new IllegalArgumentException("invalid id");
        }
        return manager.save(ContactMapper.INSTANCE.updateContactEntity(existingDto, updateDto));
    }

    public ContactDto findById(Long id) {
        return manager.findById(id);
    }

    public Void delete(Long id) {
        Void ans = null;
        manager.delete(id);
        return ans;
    }

    public List<ContactDto> filter(String firstName, String lastName, String email) {
        if (ObjectUtils.isNotEmpty(firstName)) {
            return manager.findByFirstName(firstName);
        } else if (ObjectUtils.isNotEmpty(lastName)) {
            return manager.findByLastName(lastName);
        } else {
            return manager.findByEmail(email);
        }
    }
}
