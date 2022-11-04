package Events;

import java.util.EventObject;

public class RemoveKuchenEvent extends EventObject {

    private int fachnummer;

    public RemoveKuchenEvent(Object source, int fachnummer) {
        super(source);
        this.fachnummer = fachnummer;
    }

    public String toString() {
        return "remove Kuchen";
    }

    public int getFachnummer() {
        return fachnummer;
    }
}
