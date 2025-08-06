package com.example.synchronisation;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ExternalEvent extends ApplicationEvent {

    private final List<String> snapshots;
    private Instant instant;

    public ExternalEvent(Object source) {
        super(source);
        this.snapshots = (ArrayList)source;
    }

    public ExternalEvent(Object source, Clock clock) {
        super(source, clock);
        this.snapshots = (ArrayList)source;
    }
}
