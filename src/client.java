import CLI.cli;
import net.ClientUDP;

import java.net.Socket;

public class client {
    public static void main(String[] args) {

        new cli( new ClientUDP(new Socket().getLocalPort())).start();
    }
}
