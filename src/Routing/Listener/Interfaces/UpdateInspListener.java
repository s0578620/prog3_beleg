package Routing.Listener.Interfaces;

import Routing.Events.UpdateInspEvent;

public interface UpdateInspListener extends EventListener<UpdateInspEvent> {
    void onEvent(UpdateInspEvent event);
}
