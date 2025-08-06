package com.example.synchronisation;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRES_NEW)
public interface ExternalEventStorage {
    void saveExternalEvent(ExternalEvent externalEvent);
    void deleteExternalEvent(ExternalEvent externalEvent);
}
