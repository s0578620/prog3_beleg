package Events;

import java.util.EventObject;

public class ShowHerstellerEvent extends EventObject {

    private String txt;

    public ShowHerstellerEvent(Object source, String txt) {
        super(source);
        this.txt = txt;
    }

    public String getTxt() {
        return txt;
    }

    public String toString() {
        return "show Hersteller";
    }
}
