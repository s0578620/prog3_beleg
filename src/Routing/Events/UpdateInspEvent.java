package Routing.Events;

import java.util.EventObject;

public class UpdateInspEvent extends EventObject {

    private int fachnummer;

    public UpdateInspEvent(Object source, int fachnummer) {
        super(source);
        this.fachnummer = fachnummer;
    }

    public String toString(){
        return "update Inspektionsdatum";
    }

    public int getFachnummer() {
        return fachnummer;
    }
}
