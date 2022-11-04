package Events;

import java.util.EventObject;

public class AddHerstellerEvent extends EventObject {

    private String hersteller;

    public AddHerstellerEvent(Object source, String hersteller) {
        super(source);
        this.hersteller = hersteller;
    }

    public String getHersteller() {
        return hersteller;
    }


    public String toString() {
        return "add Hersteller";
    }
}
