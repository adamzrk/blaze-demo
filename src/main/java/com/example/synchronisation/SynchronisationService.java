package com.example.synchronisation;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class SynchronisationService {

    @Value("${synchronisation.batch.size:100}")
    private int batchSize;
    private final ObjectIdentifierDao objectIdentifierDao;
    private final InternalEventSender internalEventSender;


    @Transactional(timeout = 60 * 60 * 10)
    public void synchroniseWholeDatabase() {
        var identifiers = objectIdentifierDao.findAllObjectIdentifiers();

        List<ObjectIdWrapper> batch = new ArrayList<>();
        identifiers.forEach(id -> {
            if (batch.size() < batchSize) {
                batch.add(id);
            }
            if (batch.size() == batchSize) {
                internalEventSender.send(batch);
                batch.clear();
            }
        });

        if (!batch.isEmpty()) {
            internalEventSender.send(batch);
        }
    }


}
