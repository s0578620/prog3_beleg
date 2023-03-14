package Routing.Listener.Listener;

import Routing.Events.SaveFileEvent;
import GL.ObjDatabase;
import IO.FileSystem;

public class IOSafeFileListener implements Routing.Listener.Interfaces.IOSafeFileListener {

    private ObjDatabase oDB;
    private FileSystem filesystem;

    public IOSafeFileListener(ObjDatabase oDB){
        this.oDB = oDB;
        this.filesystem = new FileSystem(oDB);
    }
    @Override
    public void onEvent(SaveFileEvent event) {
        String protoAddress = event.getProtocol();
        if (protoAddress.equals("JOS") || protoAddress.equals("JBP")) {
            filesystem.saveDB(protoAddress);
        }
        oDB.notifyObservers();
    }

    @Override
    public String onEventReturn(SaveFileEvent event) {
        return null;
    }

}
