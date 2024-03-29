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
        Thread thread = new Thread(() -> {
        this.oDB.addObj(event.getKuchentyp(),event.getHersteller(),event.getPreis(),event.getNaehrwert(),event.getHaltbarkeit(),event.getAllergene(),event.getTopping());
        });
        thread.start();
    }

    @Override
    public String onEventReturn(AddKuchenEvent event) {
        return null;
    }
}
