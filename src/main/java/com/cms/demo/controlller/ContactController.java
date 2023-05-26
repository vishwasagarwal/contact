package com.cms.demo.controlller;

import com.cms.demo.mapper.ContactMapper;
import com.cms.demo.request.CreateContactRequest;
import com.cms.demo.request.UpdateContactRequest;
import com.cms.demo.response.ContactResponse;
import com.cms.demo.response.Response;
import com.cms.demo.service.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/contact")
public class ContactController {

    @Autowired
    private ContactService service;

    @Operation(summary = "create new contacts")
    @PostMapping
    public ResponseEntity<Response<ContactResponse>> createContact(@Valid @RequestBody CreateContactRequest request) {
        return ResponseEntity.ok(new Response<>(ContactMapper.INSTANCE.dtoToResponse(service.save(ContactMapper.INSTANCE.requestToDto(request)))));
    }

    @Operation(summary = "update contacts")
    @PutMapping("/{id}")
    public ResponseEntity<Response<ContactResponse>> updateContact(@PathVariable Long id,
                                                                   @RequestBody UpdateContactRequest request) {
        return ResponseEntity.ok(new Response<>(ContactMapper.INSTANCE.dtoToResponse(service.update(id, ContactMapper.INSTANCE.updateRequestToDto(request)))));
    }

    @Operation(summary = "search the contact based on first Name, Last Name, Phone Number")
    @GetMapping
    public ResponseEntity<Response<List<ContactResponse>>> filter(@RequestParam(required = false) String firstName,
                                                                  @RequestParam(required = false) String lastName,
                                                                  @RequestParam(required = false) String email) {
        if (ObjectUtils.allNull(firstName, lastName, email)) throw new IllegalArgumentException("invalid request");
        return ResponseEntity.ok(new Response<>(ContactMapper.INSTANCE.dtoListToResponseList(service.filter(firstName, lastName, email))));
    }

    @Operation(summary = "fetch the contact by its id")
    @GetMapping("/{id}")
    public ResponseEntity<Response<ContactResponse>> getContactById(@PathVariable Long id) {
        return ResponseEntity.ok(new Response<>(ContactMapper.INSTANCE.dtoToResponse(service.findById(id))));
    }

    @Operation(summary = "delete the contact by its id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Void>> deleteContact(@PathVariable Long id) {
        return ResponseEntity.ok(new Response<>(service.delete(id)));
    }
}
