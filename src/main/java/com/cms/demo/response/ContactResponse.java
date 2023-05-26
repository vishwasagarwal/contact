package com.cms.demo.response;

import lombok.Data;

@Data
public class ContactResponse {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
