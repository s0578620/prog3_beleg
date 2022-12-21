package Routing.Listener.Listener;

import Routing.Events.UpdateInspEvent;
import GL.ObjDatabase;

public class UpdateInspListener implements Routing.Listener.Interfaces.UpdateInspListener {

    private ObjDatabase oDB;

    public UpdateInspListener(ObjDatabase oDB){
        this.oDB = oDB;
    }

    @Override
    public void onEvent(UpdateInspEvent event) {
        this.oDB.updateInsp(event.getFachnummer());
    }
}
