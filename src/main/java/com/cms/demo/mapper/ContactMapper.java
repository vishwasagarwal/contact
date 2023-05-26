package com.cms.demo.mapper;

import com.cms.demo.dto.ContactDto;
import com.cms.demo.entity.Contact;
import com.cms.demo.request.CreateContactRequest;
import com.cms.demo.request.UpdateContactRequest;
import com.cms.demo.response.ContactResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ContactMapper {

    ContactMapper INSTANCE = Mappers.getMapper(ContactMapper.class);

    Contact dtoToEntity(ContactDto dto);

    ContactDto entityToDto(Contact entity);

    List<ContactDto> entitiesToDtoList(List<Contact> entities);

    ContactResponse dtoToResponse(ContactDto dto);

    ContactDto requestToDto(CreateContactRequest request);

    ContactDto updateRequestToDto(UpdateContactRequest request);

    ContactDto updateContactEntity(@MappingTarget ContactDto existingUser, ContactDto updatedUser);

    List<ContactResponse> dtoListToResponseList(List<ContactDto> filter);
}
