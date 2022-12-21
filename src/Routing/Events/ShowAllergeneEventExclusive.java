package Routing.Events;

import java.util.EventObject;

public class ShowAllergeneEventExclusive extends EventObject {

    private String txt;

    public ShowAllergeneEventExclusive(Object source, String txt) {
        super(source);
        this.txt = txt;
    }

    public String getTxt() {
        return txt;
    }


    public String toString() {
        return "show Allergene e";
    }

}
