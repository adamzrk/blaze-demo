package com.example.blaze.view;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import com.blazebit.persistence.view.Mapping;
import com.example.blaze.model.Address;

@EntityView(Address.class)
public interface AddressView {

    @IdMapping("id")
    Long getId();

    @Mapping
    String getCity();

    @Mapping
    CountryView getCountry();
}
