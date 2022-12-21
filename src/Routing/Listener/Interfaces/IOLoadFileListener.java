package Routing.Listener.Interfaces;

import Routing.Events.LoadFileEvent;

public interface IOLoadFileListener extends EventListener<LoadFileEvent> {
    void onEvent(LoadFileEvent event);
}
