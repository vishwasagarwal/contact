package com.cms.demo.entity;

import lombok.*;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;

@Getter
@Setter
@ToString(callSuper = true)
@Table(name = "contact")
@Entity(name = "contact")
@SQLDelete(sql = "update contact set deleted = true where id = ?")
@NoArgsConstructor
@AllArgsConstructor
public class Contact extends AbstractEntity {
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
}
