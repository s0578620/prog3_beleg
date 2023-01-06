package CLI;

import Routing.Handler.Handler;
import net.Client;

public class cli {

    private  Client client;
    private Handler handler;

    public cli(Handler handler){
        this.handler = handler;
    }

    public cli(Client client){
        this.client = client;
    }

    public void start(){
        if(client != null){
            new console(client).run();
        }else {
            new console(handler).run();
        }
    }
}
