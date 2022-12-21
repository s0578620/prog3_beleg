package Routing.Listener.Interfaces;

import Routing.Events.ShowKuchenEvent;

public interface ShowKuchenListener extends EventListener<ShowKuchenEvent>{
    void onEvent(ShowKuchenEvent event);
}
