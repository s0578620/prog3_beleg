package Events;

import java.util.EventObject;

public class SaveFileEvent extends EventObject {

    private String protocol;

    public SaveFileEvent(Object source, String protocol) {
        super(source);
        if(protocol == null){
            throw new IllegalArgumentException("argument missing");
        }
        this.protocol = protocol;
    }

    public String toString() {
        return "file save";
    }

    public String getProtocol() {
        return protocol;
    }

}
