package Events;

import java.util.EventObject;

public class showKuchenEvent extends EventObject {

    private String txt;


    public showKuchenEvent(Object source, String txt) {
        super(source);
        this.txt = txt;

    }
    public showKuchenEvent(Object source) {
        super(source);
    }

    public String getTxt() {
        return txt;
    }
    public String toString() {
        return "show Kuchen";
    }
}
