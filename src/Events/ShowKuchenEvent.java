package Events;

import java.util.EventObject;

public class ShowKuchenEvent extends EventObject {

    private String txt;


    public ShowKuchenEvent(Object source, String txt) {
        super(source);
        this.txt = txt;

    }

    public String getTxt() {
        return txt;
    }
    public String toString() {
        return "show Kuchen";
    }
}
