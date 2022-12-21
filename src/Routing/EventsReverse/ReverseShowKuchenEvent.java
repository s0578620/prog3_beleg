package Routing.EventsReverse;

import java.util.EventObject;
import java.util.List;

public class ReverseShowKuchenEvent extends EventObject {

    public List<String> getList() {
        return list;
    }

    private java.util.List<String> list;

    public ReverseShowKuchenEvent(Object source, List<String> list) {
        super(source);
        this.list = list;
    }

    public String toString() {
        return "show Kuchen";
    }
}
