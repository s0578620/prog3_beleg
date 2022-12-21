package Routing.Listener.Interfaces;

import Routing.Events.ShowKuchenEvent;
import Routing.Events.ShowKuchenTypEvent;

public interface ShowKuchenTypListener extends EventListener<ShowKuchenTypEvent> {
    void onEvent(ShowKuchenTypEvent event);
}
