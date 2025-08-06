package com.example.blaze.projection;

import lombok.Value;

import java.util.List;

@Value
public class PersonDTO {
    private final Long id;
    private final String firstName;
    private final String lastName;
    private List<AddressDTO> adresses;
    private List<TelephoneDTO> telephones;
}
