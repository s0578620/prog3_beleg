package Routing.Listener.Interfaces;

import Routing.Events.ShowHerstellerEvent;

public interface ShowHerstellerListener extends EventListener<ShowHerstellerEvent>{
    void onEvent(ShowHerstellerEvent event);
}
