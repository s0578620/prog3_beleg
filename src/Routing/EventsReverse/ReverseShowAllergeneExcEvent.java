package Routing.EventsReverse;

import java.util.EventObject;
import java.util.List;

public class ReverseShowAllergeneExcEvent extends EventObject {

    private java.util.List<String> list;


    public ReverseShowAllergeneExcEvent(Object source, List<String> list) {
        super(source);
        this.list = list;

    }

    public List<String> getList() {
        return list;
    }

    public String toString() {
        return "show Allergene Exclusive";
    }
}
