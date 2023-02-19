package Routing.Listener.InterfacesReverse;

import Routing.EventsReverse.ReverseShowHerstellerEvent;
import Routing.Listener.Interfaces.EventListener;

public interface ReverseShowHerstellerListener extends EventListener<ReverseShowHerstellerEvent> {
    void onEvent(ReverseShowHerstellerEvent event);
    String onEventReturn(ReverseShowHerstellerEvent event);
}
