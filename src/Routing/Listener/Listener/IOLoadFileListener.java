package Routing.Listener.Listener;

import Routing.Events.LoadFileEvent;
import GL.ObjDatabase;
import IO.FileSystem;

public class IOLoadFileListener implements Routing.Listener.Interfaces.IOLoadFileListener {

    private ObjDatabase oDB;
    private FileSystem filesystem;

    public IOLoadFileListener(ObjDatabase oDB){
        this.oDB = oDB;
        this.filesystem = new FileSystem(oDB);
    }


    @Override
    public void onEvent(LoadFileEvent event) {
        String protoAddress = event.getProtocol();
        if ( protoAddress.equals("JOS") || protoAddress.equals("JBP")) {
            filesystem.loadDB(protoAddress);
        }
        oDB.notifyObservers();
    }

    @Override
    public String onEventReturn(LoadFileEvent event) {
        return null;
    }
}
