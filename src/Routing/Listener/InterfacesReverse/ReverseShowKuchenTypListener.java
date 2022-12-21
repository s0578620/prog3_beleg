package Routing.Listener.InterfacesReverse;

import Routing.EventsReverse.ReverseShowKuchenEvent;
import Routing.EventsReverse.ReverseShowKuchenTypEvent;
import Routing.Listener.Interfaces.EventListener;

public interface ReverseShowKuchenTypListener extends EventListener<ReverseShowKuchenTypEvent> {
    void onEvent(ReverseShowKuchenTypEvent event);
}
