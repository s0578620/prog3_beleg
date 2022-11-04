package CLI;

import Events.EventHandler;

public class cli {

    private EventHandler handler;

    public cli(EventHandler handler){
        this.handler = handler;
    }

    public void start(){
        if(handler != null){
            new console(handler).run();
        }
    }

}
