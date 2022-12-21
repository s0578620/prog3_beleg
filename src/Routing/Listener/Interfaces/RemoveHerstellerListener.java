package Routing.Listener.Interfaces;

import Routing.Events.RemoveHerstellerEvent;

public interface RemoveHerstellerListener extends EventListener<RemoveHerstellerEvent> {
    void onEvent(RemoveHerstellerEvent event);
}
