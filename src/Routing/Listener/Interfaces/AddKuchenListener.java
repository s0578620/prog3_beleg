package Routing.Listener.Interfaces;

import Routing.Events.AddKuchenEvent;

public interface AddKuchenListener extends EventListener<AddKuchenEvent> {
    void onEvent(AddKuchenEvent event);
}
