package com.example.blaze.view;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import com.blazebit.persistence.view.Mapping;
import com.example.blaze.model.Country;

@EntityView(Country.class)
public interface CountryView {

    @IdMapping("id")
    Long getId();

    @Mapping
    String getCode();

    @Mapping
    String getName();
}
