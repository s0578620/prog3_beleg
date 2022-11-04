package Events;

import java.util.EventObject;

public class ShowKuchenTypEvent extends EventObject {

    private String txt;

    public String getTyp() {
        return typ;
    }

    private String typ;

    public ShowKuchenTypEvent(Object source, String txt, String typ) {
        super(source);
        this.txt = txt;
        this.typ = typ;
    }

    public String toString() {
        return "show KuchenTyp";
    }
}
