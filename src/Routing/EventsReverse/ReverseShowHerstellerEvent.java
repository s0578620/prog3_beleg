package Routing.EventsReverse;

import java.util.EventObject;
import java.util.List;

public class ReverseShowHerstellerEvent extends EventObject {
    private java.util.List<String> list;

    public ReverseShowHerstellerEvent(Object source, List<String> list) {
        super(source);
        this.list = list;
    }

    public List<String> getList() {
        return list;
    }

    public String toString() {
        return "show Hersteller";
    }
}
