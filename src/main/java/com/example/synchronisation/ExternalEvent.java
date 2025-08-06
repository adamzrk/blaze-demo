package com.example.synchronisation;

import lombok.Value;
import org.springframework.context.ApplicationEvent;

import java.time.Instant;
import java.util.List;

@Value
public class ExternalEvent extends ApplicationEvent {

    private final List<String> snapshots;
    private Instant instant;
}
