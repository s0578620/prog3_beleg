package Routing.Listener.Interfaces;

import Routing.Events.SaveFileEvent;

public interface IOSafeFileListener extends EventListener<SaveFileEvent> {
    void onEvent(SaveFileEvent event);
}
