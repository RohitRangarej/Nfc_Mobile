package com.nfc.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "personal_info")
public class PersonalInfo {

    @Id
    private String id;

    private String coverPhoto;
    private String profilePhoto;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String phoneNumber;
    private String telephoneNumber;
    private String aboutUs;
    private String company;
    private String address1;
    private String address2;

    private List<String> gallery = new ArrayList<>();
    private List<String> socialLinks = new ArrayList<>();
    private List<Product> products;
    private List<Service> services;
    
}
