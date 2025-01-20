package com.screen;

import javafx.event.Event;
import javafx.event.EventType;

public class UpdateButtonsVisibilityEvent extends Event {
    public static final EventType<UpdateButtonsVisibilityEvent> UPDATE_VISIBILITY =
            new EventType<>(Event.ANY, "UPDATE_VISIBILITY");

    public UpdateButtonsVisibilityEvent() {
        super(UPDATE_VISIBILITY);
    }
}
