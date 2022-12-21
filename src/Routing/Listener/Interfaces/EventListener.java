package Routing.Listener.Interfaces;

import java.util.EventObject;

public interface EventListener<T extends EventObject> extends java.util.EventListener {
    void onEvent(T event);
}

