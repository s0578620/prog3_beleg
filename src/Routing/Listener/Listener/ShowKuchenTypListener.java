package Routing.Listener.Listener;


import GL.ObjDatabase;
import Routing.Events.ShowKuchenEvent;
import Routing.Events.ShowKuchenTypEvent;
import Routing.EventsReverse.ReverseShowKuchenEvent;
import Routing.Handler.AddHerstellerHandler;
import Routing.Listener.Interfaces.ShowKuchenListener;

public class ShowKuchenTypListener implements Routing.Listener.Interfaces.ShowKuchenTypListener {

    private ObjDatabase oDB;
    private AddHerstellerHandler handler;

    public ShowKuchenTypListener(ObjDatabase oDB, AddHerstellerHandler handler){
        this.oDB = oDB;
        this.handler = handler;
    }


    @Override
    public void onEvent(ShowKuchenTypEvent event) {
        if(null!=this.handler){
            handler.handle(new ReverseShowKuchenEvent(this, oDB.showKuchen(event.getTyp())));
        }
    }
}
