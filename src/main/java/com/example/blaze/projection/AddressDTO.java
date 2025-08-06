package com.example.blaze.projection;

import lombok.Value;

@Value
public class AddressDTO {
    private final String city;
    private final CountryDTO country;
}
