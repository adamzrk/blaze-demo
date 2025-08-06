package com.example.synchronisation;

import lombok.Getter;
import lombok.Value;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;
import java.util.ArrayList;
import java.util.List;

@Getter
public class InternalEvent extends ApplicationEvent {

    private final List<ObjectIdWrapper> objectIds;

    public InternalEvent(Object source) {
        super(source);
        this.objectIds = (ArrayList)source;
    }

    public InternalEvent(Object source, Clock clock) {
        super(source, clock);
        this.objectIds = (ArrayList)source;
    }
}
