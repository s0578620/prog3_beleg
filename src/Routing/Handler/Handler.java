package Routing.Handler;

import Routing.Listener.Interfaces.EventListener;
import java.util.*;

public class Handler {

    private Map<Class<? extends EventObject>, List<Routing.Listener.Interfaces.EventListener>> listeners = new HashMap<>();

    public <T extends EventObject> void addListener(Class<T> eventType, Routing.Listener.Interfaces.EventListener<T> listener) {
        List<Routing.Listener.Interfaces.EventListener> eventListeners = listeners.get(eventType);
        if (eventListeners == null) {
            eventListeners = new LinkedList<>();
            listeners.put(eventType, eventListeners);
        }
        eventListeners.add(listener);
    }

    public <T extends EventObject> void removeListener(Class<T> eventType, Routing.Listener.Interfaces.EventListener<T> listener) {
        List<Routing.Listener.Interfaces.EventListener> eventListeners = listeners.get(eventType);
        if (eventListeners != null) {
            eventListeners.remove(listener);
        }
    }

    public <T extends EventObject> void handle(T event) {
        Class<? extends EventObject> eventType = event.getClass();
        List<Routing.Listener.Interfaces.EventListener> eventListeners = listeners.get(eventType);
        if (eventListeners != null) {
            for (EventListener<T> listener : eventListeners) {
                listener.onEvent(event);
            }
        }
    }
}
