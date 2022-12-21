package Routing.Listener.Interfaces;

import Routing.Events.AddHerstellerEvent;

public interface AddHerstellerListener extends EventListener<AddHerstellerEvent> {

    void onEvent(AddHerstellerEvent event);
}
