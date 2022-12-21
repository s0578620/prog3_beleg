package Routing.Listener.Listener;

import Routing.Handler.AddHerstellerHandler;
import Routing.EventsReverse.ReverseShowKuchenEvent;
import Routing.Events.ShowKuchenEvent;
import GL.ObjDatabase;

public class ShowKuchenListener implements Routing.Listener.Interfaces.ShowKuchenListener {

    private ObjDatabase oDB;
    private AddHerstellerHandler handler;

    public ShowKuchenListener(ObjDatabase oDB, AddHerstellerHandler handler){
        this.oDB = oDB;
        this.handler = handler;
    }


    @Override
    public void onEvent(ShowKuchenEvent event) {
        if(null!=this.handler){
            handler.handle(new ReverseShowKuchenEvent(this, oDB.showKuchenAll()));
        }
    }
}
