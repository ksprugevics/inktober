package org.inktober.config;

import lombok.extern.slf4j.Slf4j;
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
