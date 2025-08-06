package com.example.synchronisation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.Semaphore;

@Component
@RequiredArgsConstructor
public class ExternalSystemSender {

    private Semaphore semaphore = new Semaphore(1000);
    private final ExternalEventPublisher externalEventPublisher;
    private final ExternalEventStorage externalEventStorage;

    public void sendEvent(ExternalEvent externalEvent) {
        if (semaphore.tryAcquire()) {
            try {
                //done outside transaction - at this point we know that there are enough of resources
                sendToExternalSystem(externalEvent);
                externalEventStorage.deleteExternalEvent(externalEvent);
            } finally {
                semaphore.release();
            }
        }
    }

    private void sendToExternalSystem(ExternalEvent externalEvent) {
        externalEventPublisher.publishExternalEvent(externalEvent);
    }
}
