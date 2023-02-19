import CLI.cli;
import net.ClientTCP;
import net.ClientUDP;

public class NetClient {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: java client <protocol>");
            System.exit(1);
        }

        String protocol = args[0];

        if (protocol.equals("tcp")) {
            new cli(new ClientTCP()).start();
        } else if (protocol.equalsIgnoreCase("udp")) {
            new cli(new ClientUDP()).start();
        } else {
            System.err.println("Invalid protocol: " + protocol);
            System.exit(1);
        }
    }
}