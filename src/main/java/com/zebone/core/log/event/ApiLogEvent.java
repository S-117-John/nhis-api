package com.zebone.core.log.event;

import org.springframework.context.ApplicationEvent;

public class ApiLogEvent extends ApplicationEvent {
    public ApiLogEvent(Object source) {
        super(source);
    }
}
