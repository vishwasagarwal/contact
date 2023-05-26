package com.cms.demo.mapper;

import com.cms.demo.dto.ContactDto;
import com.cms.demo.entity.Contact;
import com.cms.demo.request.CreateContactRequest;
import com.cms.demo.request.UpdateContactRequest;
import com.cms.demo.response.ContactResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-26T23:50:24+0530",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.2 (Amazon.com Inc.)"
)
public class ContactMapperImpl implements ContactMapper {

    @Override
    public Contact dtoToEntity(ContactDto dto) {
        if ( dto == null ) {
            return null;
        }

        Contact contact = new Contact();

        contact.setId( dto.getId() );
        contact.setCreated( dto.getCreated() );
        contact.setUpdated( dto.getUpdated() );
        contact.setDeleted( dto.isDeleted() );
        contact.setFirstName( dto.getFirstName() );
        contact.setLastName( dto.getLastName() );
        contact.setEmail( dto.getEmail() );
        contact.setPhoneNumber( dto.getPhoneNumber() );

        return contact;
    }

    @Override
    public ContactDto entityToDto(Contact entity) {
        if ( entity == null ) {
            return null;
        }

        ContactDto contactDto = new ContactDto();

        contactDto.setId( entity.getId() );
        contactDto.setCreated( entity.getCreated() );
        contactDto.setUpdated( entity.getUpdated() );
        contactDto.setDeleted( entity.isDeleted() );
        contactDto.setFirstName( entity.getFirstName() );
        contactDto.setLastName( entity.getLastName() );
        contactDto.setEmail( entity.getEmail() );
        contactDto.setPhoneNumber( entity.getPhoneNumber() );

        return contactDto;
    }

    @Override
    public List<ContactDto> entitiesToDtoList(List<Contact> entities) {
        if ( entities == null ) {
            return null;
        }

        List<ContactDto> list = new ArrayList<ContactDto>( entities.size() );
        for ( Contact contact : entities ) {
            list.add( entityToDto( contact ) );
        }

        return list;
    }

    @Override
    public ContactResponse dtoToResponse(ContactDto dto) {
        if ( dto == null ) {
            return null;
        }

        ContactResponse contactResponse = new ContactResponse();

        contactResponse.setId( dto.getId() );
        contactResponse.setFirstName( dto.getFirstName() );
        contactResponse.setLastName( dto.getLastName() );
        contactResponse.setEmail( dto.getEmail() );
        contactResponse.setPhoneNumber( dto.getPhoneNumber() );

        return contactResponse;
    }

    @Override
    public ContactDto requestToDto(CreateContactRequest request) {
        if ( request == null ) {
            return null;
        }

        ContactDto contactDto = new ContactDto();

        contactDto.setFirstName( request.getFirstName() );
        contactDto.setLastName( request.getLastName() );
        contactDto.setEmail( request.getEmail() );
        contactDto.setPhoneNumber( request.getPhoneNumber() );

        return contactDto;
    }

    @Override
    public ContactDto updateRequestToDto(UpdateContactRequest request) {
        if ( request == null ) {
            return null;
        }

        ContactDto contactDto = new ContactDto();

        contactDto.setFirstName( request.getFirstName() );
        contactDto.setLastName( request.getLastName() );
        contactDto.setEmail( request.getEmail() );

        return contactDto;
    }

    @Override
    public ContactDto updateContactEntity(ContactDto existingUser, ContactDto updatedUser) {
        if ( updatedUser == null ) {
            return existingUser;
        }

        existingUser.setId( updatedUser.getId() );
        if ( updatedUser.getCreated() != null ) {
            existingUser.setCreated( updatedUser.getCreated() );
        }
        if ( updatedUser.getUpdated() != null ) {
            existingUser.setUpdated( updatedUser.getUpdated() );
        }
        existingUser.setDeleted( updatedUser.isDeleted() );
        if ( updatedUser.getFirstName() != null ) {
            existingUser.setFirstName( updatedUser.getFirstName() );
        }
        if ( updatedUser.getLastName() != null ) {
            existingUser.setLastName( updatedUser.getLastName() );
        }
        if ( updatedUser.getEmail() != null ) {
            existingUser.setEmail( updatedUser.getEmail() );
        }
        if ( updatedUser.getPhoneNumber() != null ) {
            existingUser.setPhoneNumber( updatedUser.getPhoneNumber() );
        }

        return existingUser;
    }

    @Override
    public List<ContactResponse> dtoListToResponseList(List<ContactDto> filter) {
        if ( filter == null ) {
            return null;
        }

        List<ContactResponse> list = new ArrayList<ContactResponse>( filter.size() );
        for ( ContactDto contactDto : filter ) {
            list.add( dtoToResponse( contactDto ) );
        }

        return list;
    }
}
