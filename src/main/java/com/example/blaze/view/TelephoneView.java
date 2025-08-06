package com.example.blaze.view;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import com.blazebit.persistence.view.Mapping;
import com.example.blaze.model.Telephone;

@EntityView(Telephone.class)
public interface TelephoneView {

    @IdMapping("id")
    Long getId();

    @Mapping
    String getModel();

    @Mapping
    String getNumber();
}
