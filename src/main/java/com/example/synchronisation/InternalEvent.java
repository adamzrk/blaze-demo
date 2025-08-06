package com.example.synchronisation;

import lombok.Value;
import org.springframework.context.ApplicationEvent;

import java.util.List;

@Value
public class InternalEvent extends ApplicationEvent {

    private final List<ObjectIdWrapper> objectIds;

}
