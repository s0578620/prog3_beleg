package Events;

import java.util.EventObject;

public class removeKuchenEvent extends EventObject {

    private int fachnummer;

    public removeKuchenEvent(Object source, int fachnummer) {
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
