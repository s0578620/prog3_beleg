package CLI;

//import Events.EventHandler;
import Routing.Handler.AddHerstellerHandler;

public class cli {

    private AddHerstellerHandler handler;

    public cli(AddHerstellerHandler handler){
        this.handler = handler;
    }

    public void start(){
        if(handler != null){
            new console(handler).run();
        }
    }

}
