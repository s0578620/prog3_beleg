package Routing.Listener.Listener;

import GL.ObjDatabase;
import Routing.Events.ShowAllergeneEventInclusive;
import Routing.EventsReverse.ReverseShowAllergeneIncEvent;
import Routing.Handler.Handler;

public class ShowAllergeneIncListener implements Routing.Listener.Interfaces.ShowAllergeneIncListener {

    private ObjDatabase oDB;
    private Handler handler;

    public ShowAllergeneIncListener(ObjDatabase oDB, Handler handler){
        this.oDB = oDB;
        this.handler = handler;
    }
    @Override
    public void onEvent(ShowAllergeneEventInclusive event) {
        if(null!=this.handler){
            handler.handle(new ReverseShowAllergeneIncEvent(this, oDB.showAllergene(true)));
        }
    }

    @Override
    public String onEventReturn(ShowAllergeneEventInclusive event) {
        if(null!=this.handler){
            return handler.handleReturn(new ReverseShowAllergeneIncEvent(this, oDB.showAllergene(true)));
        }
        return null;
    }
}
