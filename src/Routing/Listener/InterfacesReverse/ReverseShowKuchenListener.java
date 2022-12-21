package Routing.Listener.InterfacesReverse;

import Routing.Listener.Interfaces.EventListener;
import Routing.EventsReverse.ReverseShowKuchenEvent;

public interface ReverseShowKuchenListener extends EventListener<ReverseShowKuchenEvent> {
    void onEvent(ReverseShowKuchenEvent event);
}
