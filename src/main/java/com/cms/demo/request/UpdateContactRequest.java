package com.cms.demo.request;

import lombok.Data;

@Data
public class UpdateContactRequest {
    private String firstName;
    private String lastName;
    private String email;
}
