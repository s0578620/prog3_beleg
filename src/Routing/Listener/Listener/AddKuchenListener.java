package Routing.Listener.Listener;

import Routing.Events.AddKuchenEvent;
import GL.ObjDatabase;

public class AddKuchenListener implements Routing.Listener.Interfaces.AddKuchenListener {

    private ObjDatabase oDB;

    public AddKuchenListener(ObjDatabase oDB){
        this.oDB = oDB;
    }
    @Override
    public void onEvent(AddKuchenEvent event) {
        this.oDB.addObj(event.getKuchentyp(),event.getHersteller(),event.getPreis(),event.getNaehrwert(),event.getHaltbarkeit(),event.getAllergene(),event.getTopping());

    }
}
