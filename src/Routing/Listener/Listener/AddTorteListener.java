package Routing.Listener.Listener;

import Routing.Events.AddTorteEvent;
import GL.ObjDatabase;

public class AddTorteListener implements Routing.Listener.Interfaces.AddTorteListener {

    private ObjDatabase oDB;

    public AddTorteListener(ObjDatabase oDB){
        this.oDB = oDB;
    }
    @Override
    public void onEvent(AddTorteEvent event) {
        Thread thread = new Thread(() -> {
            this.oDB.addObj(event.getKuchentyp(), event.getHersteller(),event.getPreis(),event.getNaehrwert(),event.getHaltbarkeit(),event.getAllergene(),event.getKremsorte(),event.getObstsorte());
        });
        thread.start();
    }

    @Override
    public String onEventReturn(AddTorteEvent event) {
        return null;
    }
}
