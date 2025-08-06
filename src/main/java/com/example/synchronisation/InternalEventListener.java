package com.example.synchronisation;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class InternalEventListener implements ApplicationListener<InternalEvent> {

    private final ObjectSnapshotService objectSnapshotService;
    private final ExternalEventStorage externalEventStorage;
    private final ExternalSystemSender externalSystemSender;

    @Override
    @Async // with configured number of thread pool
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION)
    public void onApplicationEvent(InternalEvent event) {
        var snapshots = objectSnapshotService.snapshot(event.getObjectIds());
        ExternalEvent externalEvent = new ExternalEvent(snapshots, Instant.now());
        // rate limiting here based on java sempahore
        // outbox - if event is still in the database - it should be sent later
        externalEventStorage.saveExternalEvent(externalEvent);
        externalSystemSender.sendEvent(externalEvent);
    }
}
