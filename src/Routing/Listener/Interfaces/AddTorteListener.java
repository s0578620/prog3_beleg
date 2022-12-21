package Routing.Listener.Interfaces;

import Routing.Events.AddTorteEvent;

public interface AddTorteListener extends EventListener<AddTorteEvent> {
    void onEvent(AddTorteEvent event);
}
