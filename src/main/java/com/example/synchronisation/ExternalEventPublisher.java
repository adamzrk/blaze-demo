package com.example.synchronisation;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class ExternalEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public void publishExternalEvent(ExternalEvent externalEvent) {
        applicationEventPublisher.publishEvent(externalEvent);
    }

}
