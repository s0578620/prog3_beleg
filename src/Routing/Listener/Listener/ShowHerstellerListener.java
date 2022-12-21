package Routing.Listener.Listener;

import GL.ObjDatabase;
import Routing.Events.ShowHerstellerEvent;
import Routing.EventsReverse.ReverseShowHerstellerEvent;
import Routing.Handler.AddHerstellerHandler;

public class ShowHerstellerListener implements Routing.Listener.Interfaces.ShowHerstellerListener {

    private ObjDatabase oDB;
    private AddHerstellerHandler handler;

    public ShowHerstellerListener(ObjDatabase oDB, AddHerstellerHandler handler){
        this.oDB = oDB;
        this.handler = handler;
    }
    @Override
    public void onEvent(ShowHerstellerEvent event) {
        if(null!=this.handler){
            handler.handle(new ReverseShowHerstellerEvent(this, oDB.showAllCakesSortedByHersteller()));
        }
    }
}
