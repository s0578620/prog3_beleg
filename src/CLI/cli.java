package CLI;

//import Events.EventHandler;
import Routing.Handler.Handler;

public class cli {

    private Handler handler;

    public cli(Handler handler){
        this.handler = handler;
    }

    public void start(){
        if(handler != null){
            new console(handler).run();
        }
    }

}
