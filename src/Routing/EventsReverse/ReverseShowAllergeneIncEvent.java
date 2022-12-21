package Routing.EventsReverse;

import java.util.EventObject;
import java.util.List;

public class ReverseShowAllergeneIncEvent extends EventObject {

    private java.util.List<String> list;


    public List<String> getList() {
        return list;
    }

    public String toString() {
        return "show Allergene Inclusive";
    }
    public ReverseShowAllergeneIncEvent(Object source, List<String> list) {
        super(source);
        this.list = list;

    }
}
