package Routing.Listener.ListenerReverse;

import Routing.EventsReverse.ReverseShowKuchenEvent;

public class ReverseShowKuchenListener implements Routing.Listener.InterfacesReverse.ReverseShowKuchenListener {


    @Override
    public void onEvent(ReverseShowKuchenEvent event) {
        event.getList().forEach(System.out::println);
    }
}
