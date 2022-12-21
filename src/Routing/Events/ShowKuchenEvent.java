package Routing.Events;

import java.util.EventObject;

public class ShowKuchenEvent extends EventObject {



    public ShowKuchenEvent(Object source) {
        super(source);
    }


    public String toString() {
        return "show Kuchen";
    }
}
