package Routing.Listener.ListenerReverse;

import Routing.EventsReverse.ReverseShowKuchenTypEvent;
import Routing.Listener.Interfaces.EventListener;

public class ReverseShowKuchenTypListener implements Routing.Listener.InterfacesReverse.ReverseShowKuchenTypListener {


    @Override
    public void onEvent(ReverseShowKuchenTypEvent event) {
        event.getList().forEach(System.out::println);
    }
}
