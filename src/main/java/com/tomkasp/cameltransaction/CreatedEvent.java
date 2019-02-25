package com.tomkasp.cameltransaction;

import org.springframework.context.ApplicationEvent;

/**
 * @author Tomasz Kasprzycki
 */
public class CreatedEvent extends ApplicationEvent {

    public CreatedEvent(String source) {
        super(source);
    }
}
