package Routing.Listener.Listener;


import GL.ObjDatabase;
import Routing.Events.ShowKuchenTypEvent;
import Routing.EventsReverse.ReverseShowKuchenEvent;
import Routing.Handler.Handler;

public class ShowKuchenTypListener implements Routing.Listener.Interfaces.ShowKuchenTypListener {

    private ObjDatabase oDB;
    private Handler handler;

    public ShowKuchenTypListener(ObjDatabase oDB, Handler handler){
        this.oDB = oDB;
        this.handler = handler;
    }


    @Override
    public void onEvent(ShowKuchenTypEvent event) {
        if(null!=this.handler){
            handler.handle(new ReverseShowKuchenEvent(this, oDB.showKuchen(event.getTyp())));
        }
    }

    @Override
    public String onEventReturn(ShowKuchenTypEvent event) {
        if(null!=this.handler){
            return handler.handleReturn(new ReverseShowKuchenEvent(this, oDB.showKuchen(event.getTyp())));
        }
        return null;
    }
}
