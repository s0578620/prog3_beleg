package Events;

import java.util.EventObject;

public class ShowAllergeneEventInclusive extends EventObject {

    private String txt;

    public ShowAllergeneEventInclusive(Object source, String txt) {
        super(source);
        this.txt = txt;
    }

    public String getTxt() {
        return txt;
    }


    public String toString() {
        return "show Allergene i";
    }
}
