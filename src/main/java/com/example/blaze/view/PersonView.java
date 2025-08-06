package com.example.blaze.view;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import com.blazebit.persistence.view.Mapping;
import com.example.blaze.model.Address;
import com.example.blaze.model.Person;


import java.util.List;

@EntityView(Person.class)
public interface PersonView {

    @IdMapping("id")
    Long getId();

    @Mapping
    String getFirstName();

    @Mapping
    String getLastName();

    @Mapping
    List<AddressView> getAddresses();

    @Mapping
    List<TelephoneView> getPhones();
}
