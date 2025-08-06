package com.example.blaze.projection;

import lombok.Value;

import java.util.List;

@Value
public class IntermediatePersonDTO {
    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String city;
    private final String countryCode;
    private final String countryName;
    private final String telephoneModel;
    private final String telephoneNumber;
}
