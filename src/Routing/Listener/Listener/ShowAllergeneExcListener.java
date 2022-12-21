package Routing.Listener.Listener;

import GL.ObjDatabase;
import Routing.Events.ShowAllergeneEventExclusive;
import Routing.EventsReverse.ReverseShowAllergeneExcEvent;
import Routing.EventsReverse.ReverseShowAllergeneIncEvent;
import Routing.Handler.Handler;

public class ShowAllergeneExcListener implements Routing.Listener.Interfaces.ShowAllergeneExcListener {

    private ObjDatabase oDB;
    private Handler handler;

    public ShowAllergeneExcListener(ObjDatabase oDB, Handler handler){
        this.oDB = oDB;
        this.handler = handler;
    }
    @Override
    public void onEvent(ShowAllergeneEventExclusive event) {
        if(null!=this.handler){
            handler.handle(new ReverseShowAllergeneExcEvent(this, oDB.showAllergene(false)));
        }
    }
}
