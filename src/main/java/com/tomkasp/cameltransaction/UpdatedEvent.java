package com.tomkasp.cameltransaction;

import com.apple.eawt.AppEvent;
import org.springframework.context.ApplicationEvent;

/**
 * @author Tomasz Kasprzycki
 */
public class UpdatedEvent extends ApplicationEvent {
    public UpdatedEvent(String source) {
        super(source);
    }
}
