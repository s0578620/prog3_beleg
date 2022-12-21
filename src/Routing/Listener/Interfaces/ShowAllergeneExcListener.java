package Routing.Listener.Interfaces;

import Routing.Events.ShowAllergeneEventExclusive;

public interface ShowAllergeneExcListener extends EventListener<ShowAllergeneEventExclusive> {
    void onEvent(ShowAllergeneEventExclusive event);
}
