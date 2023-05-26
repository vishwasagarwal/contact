package com.cms.demo.dto;

import lombok.Data;
import java.util.Date;

@Data
public class ContactDto {
    private long id;
    private Date created;
    private Date updated;
    private boolean deleted;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
