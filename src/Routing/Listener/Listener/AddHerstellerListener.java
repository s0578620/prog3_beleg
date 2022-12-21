package Routing.Listener.Listener;

import Routing.Events.AddHerstellerEvent;
import GL.ObjDatabase;

public class AddHerstellerListener implements Routing.Listener.Interfaces.AddHerstellerListener {

    private ObjDatabase oDB;

    public AddHerstellerListener(ObjDatabase oDB){
        this.oDB = oDB;
    }


    @Override
    public void onEvent(AddHerstellerEvent event) {
        this.oDB.addHersteller(event.getHersteller());
    }


}
