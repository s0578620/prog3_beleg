package Routing.Listener.InterfacesReverse;

import Routing.Events.ShowHerstellerEvent;
import Routing.EventsReverse.ReverseShowHerstellerEvent;
import Routing.Listener.Interfaces.EventListener;

public interface ReverseShowHerstellerListener extends EventListener<ReverseShowHerstellerEvent> {
    void onEvent(ReverseShowHerstellerEvent event);
}
