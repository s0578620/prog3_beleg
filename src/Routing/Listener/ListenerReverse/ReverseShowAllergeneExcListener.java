package Routing.Listener.ListenerReverse;

import Routing.EventsReverse.ReverseShowAllergeneExcEvent;

public class ReverseShowAllergeneExcListener implements Routing.Listener.InterfacesReverse.ReverseShowAllergeneExcListener {


    @Override
    public void onEvent(ReverseShowAllergeneExcEvent event) {
        event.getList().forEach(System.out::println);
    }
}
