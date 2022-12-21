package Routing.Listener.InterfacesReverse;

import Routing.EventsReverse.ReverseShowAllergeneIncEvent;
import Routing.Listener.Interfaces.EventListener;

public interface ReverseShowAllergeneIncListener extends EventListener<ReverseShowAllergeneIncEvent> {
    void onEvent(ReverseShowAllergeneIncEvent event);
}
