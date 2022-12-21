package Routing.Listener.Listener;

import GL.ObjDatabase;
import Routing.Events.ShowHerstellerEvent;
import Routing.EventsReverse.ReverseShowHerstellerEvent;
import Routing.Handler.Handler;

public class ShowHerstellerListener implements Routing.Listener.Interfaces.ShowHerstellerListener {

    private ObjDatabase oDB;
    private Handler handler;

    public ShowHerstellerListener(ObjDatabase oDB, Handler handler){
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
