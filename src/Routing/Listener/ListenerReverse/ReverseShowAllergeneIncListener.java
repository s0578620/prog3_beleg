package Routing.Listener.ListenerReverse;

import Routing.EventsReverse.ReverseShowAllergeneIncEvent;

public class ReverseShowAllergeneIncListener implements Routing.Listener.InterfacesReverse.ReverseShowAllergeneIncListener {
    @Override
    public void onEvent(ReverseShowAllergeneIncEvent event) {
        event.getList().forEach(System.out::println);
    }
}
