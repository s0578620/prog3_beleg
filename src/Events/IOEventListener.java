package Events;

import GL.ObjDatabase;
import IO.FileSystem;

import java.util.EventObject;

public class IOEventListener implements EventListener{

    private ObjDatabase oDB;
    private FileSystem filesystem;

    public IOEventListener(ObjDatabase oDB){
        this.oDB = oDB;
        this.filesystem = new FileSystem(oDB);
    }
    @Override
    public void onEvent(EventObject event) {
        switch ( event.toString() ) {
            case "file save" -> {
                String protoAddress = ((SaveFileEvent) event).getProtocol();
                if ( protoAddress.equals( "jos" ) || protoAddress.equals( "jbp" ) )
                    filesystem.saveDB( protoAddress );

                oDB.notifyObservers();
            }
            case "file load" -> {
                String protoAddress = ((LoadFileEvent) event).getProtocol();
                if ( protoAddress.equals( "jos" ) || protoAddress.equals( "jbp" ) )
                    filesystem.loadDB( protoAddress );

                oDB.notifyObservers();
            }
        }
    }
}
