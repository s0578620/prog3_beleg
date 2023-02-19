package net;

import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.EventObject;

public class ClientTCP implements Client{

    int serverPort;
    private Socket socket;
    private InputStream inStream;
    private ObjectOutputStream outStream;
    public ClientTCP(){
        try {
            this.serverPort = 5000;
            this.socket = new Socket( InetAddress.getLocalHost(), serverPort );
            this.inStream = socket.getInputStream();
            this.outStream = new ObjectOutputStream( socket.getOutputStream() );
        } catch ( Exception e ) {
            e.printStackTrace();
            System.exit( 1 );
        }
//        this.serverPort = 5000;
//        try {
//            this.socket = new Socket();
//        } catch (Exception e) {
//            System.err.println("Error setting up TCP socket");
//            System.exit( 1 );
//        }
    }



    @Override
    public String sendEvent(EventObject event) {
        try {
            if (!socket.isConnected()) {
                throw new RuntimeException( "Connection Error with TCP socket" );
            }
            outStream.writeObject( event );
            outStream.flush();

            byte[] responseBuffer = new byte[1024];
            inStream.read( responseBuffer );
            String response = new String( responseBuffer, "UTF-8" ).trim();
            System.out.println(response);
            return response;
        } catch ( Exception e ) { e.printStackTrace(); }
        return "";
    }
}

