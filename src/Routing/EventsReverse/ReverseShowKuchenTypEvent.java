package Routing.EventsReverse;

import java.util.EventObject;
import java.util.List;

public class ReverseShowKuchenTypEvent extends EventObject {

    public List<String> getList() {
        return list;
    }

    private java.util.List<String> list;

    public ReverseShowKuchenTypEvent(Object source) {
        super(source);
    }

    public ReverseShowKuchenTypEvent(Object source, List<String> list) {
        super(source);
        this.list = list;
    }
}
