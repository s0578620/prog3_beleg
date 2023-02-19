package Routing.Listener.Listener;

import Routing.EventsReverse.ReverseShowHerstellerEvent;
import Routing.Handler.Handler;
import Routing.EventsReverse.ReverseShowKuchenEvent;
import Routing.Events.ShowKuchenEvent;
import GL.ObjDatabase;

public class ShowKuchenListener implements Routing.Listener.Interfaces.ShowKuchenListener {

    private ObjDatabase oDB;
    private Handler handler;

    public ShowKuchenListener(ObjDatabase oDB, Handler handler){
        this.oDB = oDB;
        this.handler = handler;
    }


    @Override
    public void onEvent(ShowKuchenEvent event) {
        if(null!=this.handler){
            handler.handle(new ReverseShowKuchenEvent(this, oDB.showKuchenAll()));
        }
    }

    @Override
    public String onEventReturn(ShowKuchenEvent event) {
        if(null!=this.handler){
            return handler.handleReturn(new ReverseShowKuchenEvent(this, oDB.showKuchenAll()));
        }
        return null;
    }
}
