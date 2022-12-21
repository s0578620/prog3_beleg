package Routing.Events;

import java.util.EventObject;

public class LoadFileEvent extends EventObject {

    private String protocol;

    public LoadFileEvent(Object source, String protocol) {
        super(source);
        if (protocol == null) {
            throw new IllegalArgumentException("argument missing");
        }
        this.protocol = protocol;
    }

    public String toString() {
        return "file load";
    }

    public String getProtocol() {
        return protocol;
    }
}
