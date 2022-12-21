package Routing.Listener.Interfaces;

import Routing.Events.ShowAllergeneEventInclusive;

public interface ShowAllergeneIncListener extends EventListener<ShowAllergeneEventInclusive>{
    void onEvent(ShowAllergeneEventInclusive event);
}
