package com.cms.demo.request;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class CreateContactRequest {
    private String firstName;
    private String lastName;
    @Email(message = "invalid email")
    private String email;
    @Size(min = 10 , max = 10, message = "invalid phone number")
    private String phoneNumber;

    @AssertTrue(message = "first or last name cannot be empty")
    private boolean isFirsOrLastNameValid() {
        return !StringUtils.isAllEmpty(this.firstName, this.lastName);
    }
}
