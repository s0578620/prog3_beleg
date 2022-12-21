package Routing.Listener.InterfacesReverse;

import Routing.EventsReverse.ReverseShowAllergeneExcEvent;
import Routing.Listener.Interfaces.EventListener;

public interface ReverseShowAllergeneExcListener extends EventListener<ReverseShowAllergeneExcEvent> {
    void onEvent(ReverseShowAllergeneExcEvent event);
}
