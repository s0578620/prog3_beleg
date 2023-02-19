package net;

import Routing.Handler.Handler;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.EventObject;

public class ServerTCP implements Server{

    private Handler handler;
    private ServerSocket serverSocket;
    private Socket socket;

    public ServerTCP(Handler handler){
        this.handler = handler;
    }
    @Override
    public void run() {

        try {
            socket = serverSocket.accept();
        ObjectInputStream inStream = new ObjectInputStream( socket.getInputStream() );
        OutputStream outStream = socket.getOutputStream();

        while( socket.isConnected() ) {
            String reply ="done";
            try {
                EventObject event = (EventObject) inStream.readObject();
                if(event.toString().contains("show")){
                    reply = this.handler.handleReturn(event);
                }else {
                    this.handleEvent(event);
                }
            } catch (Exception e) {
                reply = "error, got invalid event object";
            }

            byte[] replyBytes = reply.getBytes();
            outStream.write( replyBytes );
            outStream.flush();
        }
    } catch ( Exception e ) { e.printStackTrace(); }
    }

    @Override
    public void start() {

    }

    @Override
    public int init() {
        try {
            serverSocket = new ServerSocket(5000);
            return serverSocket.getLocalPort();
        } catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public void handleEvent(EventObject event) {
        this.handler.handle(event);
    }
}
