package Routing.Listener.Listener;

import Routing.Events.RemoveHerstellerEvent;
import GL.ObjDatabase;

public class RemoveHerstellerListener implements Routing.Listener.Interfaces.RemoveHerstellerListener {

    private ObjDatabase oDB;

    public RemoveHerstellerListener(ObjDatabase oDB){
        this.oDB = oDB;
    }
    @Override
    public void onEvent(RemoveHerstellerEvent event) {
        this.oDB.removeHersteller(event.getHersteller());
    }
}
