package Routing.Listener.ListenerReverse;

import Routing.EventsReverse.ReverseShowHerstellerEvent;

public class ReverseShowHerstellerListener implements Routing.Listener.InterfacesReverse.ReverseShowHerstellerListener {


    @Override
    public void onEvent(ReverseShowHerstellerEvent event) {
        event.getList().forEach(System.out::println);
    }

    @Override
    public String onEventReturn(ReverseShowHerstellerEvent event) {
       return event.getList().toString();
    }
}
