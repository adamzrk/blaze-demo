package com.example.synchronisation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InternalEventSender {

    private final InternalEventPublisher internalEventPublisher;

    @Transactional
    public void send(List<ObjectIdWrapper> batch) {
        InternalEvent internalEvent = new InternalEvent(batch);
        // call executed outside transaction context as private method does not participate in existing transaction
        sendInternalEvent(internalEvent);
    }

    private void sendInternalEvent(InternalEvent internalEvent) {
        internalEventPublisher.publishInternalEvent(internalEvent);
    }

}
