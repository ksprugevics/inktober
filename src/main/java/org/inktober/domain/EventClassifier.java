package org.inktober.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum EventClassifier {

    NONE         (-1, "See you next year"),
    INKTOBER_2024(1, "Inktober 2024"),
    INKTOBER_2025(2, "Inktober 2025");

    public final int id;
    public final String eventName;

    public static EventClassifier byName(String name) {
        for (EventClassifier value : EventClassifier.values()) {
            if (value.eventName.equals(name)) {
                return value;
            }
        }

        return NONE;
    }
}
