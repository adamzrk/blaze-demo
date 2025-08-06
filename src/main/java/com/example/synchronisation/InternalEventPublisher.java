package com.example.synchronisation;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InternalEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public void publishInternalEvent(InternalEvent internalEvent) {
        applicationEventPublisher.publishEvent(internalEvent);
    }
}