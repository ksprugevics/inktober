package org.inktober.config;

import org.inktober.domain.EventClassifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventConfig {

    public final EventClassifier activeEvent;

    public EventConfig(@Value("${event.active}") String event) {
        activeEvent = EventClassifier.byName(event);
    }
}
