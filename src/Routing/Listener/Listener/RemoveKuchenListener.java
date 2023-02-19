package Routing.Listener.Listener;

import Routing.Events.RemoveKuchenEvent;
import GL.ObjDatabase;

public class RemoveKuchenListener implements Routing.Listener.Interfaces.RemoveKuchenListener {

    private ObjDatabase oDB;

    public RemoveKuchenListener(ObjDatabase oDB){
        this.oDB = oDB;
    }
    @Override
    public void onEvent(RemoveKuchenEvent event) {
        this.oDB.removeObj(event.getFachnummer());
    }

    @Override
    public String onEventReturn(RemoveKuchenEvent event) {
        return null;
    }
}
