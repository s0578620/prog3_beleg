package Events;

import java.util.EventObject;

public class RemoveHerstellerEvent extends EventObject {

    private String hersteller;

    public RemoveHerstellerEvent(Object source, String hersteller) {
        super(source);
        this.hersteller = hersteller;
    }

    public String toString() {
        return "remove Hersteller";
    }

    public String getHersteller() {
        return hersteller;
    }

}
