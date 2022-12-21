package Routing.Listener.Interfaces;

import Routing.Events.RemoveKuchenEvent;

public interface RemoveKuchenListener extends EventListener<RemoveKuchenEvent> {
    void onEvent(RemoveKuchenEvent event);
}
