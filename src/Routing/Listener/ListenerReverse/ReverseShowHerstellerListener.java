package Routing.Listener.ListenerReverse;

import Routing.Events.ShowHerstellerEvent;
import Routing.EventsReverse.ReverseShowHerstellerEvent;

public class ReverseShowHerstellerListener implements Routing.Listener.InterfacesReverse.ReverseShowHerstellerListener {


    @Override
    public void onEvent(ReverseShowHerstellerEvent event) {
        event.getList().forEach(System.out::println);
    }
}
