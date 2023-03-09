package CLI;

import Routing.Handler.Handler;
import net.Client;

public class Cli {

    private  Client client;
    private Handler handler;

    public Cli(Handler handler){
        this.handler = handler;
    }

    public Cli(Client client){
        this.client = client;
    }

    public void start(){
        if(client != null){
            new Console(client).run();
        }else {
            new Console(handler).run();
        }
    }
}
