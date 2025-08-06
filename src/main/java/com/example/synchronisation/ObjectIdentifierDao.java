package com.example.synchronisation;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ObjectIdentifierDao {

    List<ObjectIdWrapper> findAllObjectIdentifiers();

}
