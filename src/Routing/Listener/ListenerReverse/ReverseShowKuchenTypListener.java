package Routing.Listener.ListenerReverse;

import Routing.EventsReverse.ReverseShowKuchenTypEvent;

public class ReverseShowKuchenTypListener implements Routing.Listener.InterfacesReverse.ReverseShowKuchenTypListener {


    @Override
    public void onEvent(ReverseShowKuchenTypEvent event) {
        event.getList().forEach(System.out::println);
    }

    @Override
    public String onEventReturn(ReverseShowKuchenTypEvent event) {
        return event.getList().toString();
    }
}
