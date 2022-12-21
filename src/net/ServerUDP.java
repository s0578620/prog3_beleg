package net;

import Routing.Handler.Handler;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.EventObject;

public class ServerUDP implements Server{

    private Handler handler;
    private DatagramSocket socket;

    public ServerUDP(Handler handler){
        this.handler = handler;
    }

    @Override
    public void run() {
        try {
            byte[] buffer = new byte[1024];

            while (true){
                DatagramPacket packetIn = new DatagramPacket(buffer, buffer.length);
                socket.receive(packetIn);

                ByteArrayInputStream byteInputStream = new ByteArrayInputStream(packetIn.getData());
                ObjectInputStream objectInputStream = new ObjectInputStream(byteInputStream);

                String responseString = "done";
                try {
                    EventObject event = (EventObject) objectInputStream.readObject();
                    this.handleEvent(event);
                }catch (Exception e){
                    responseString = "Invalid Command";
                }

                byte[] response = responseString.getBytes();
                DatagramPacket packetOut = new DatagramPacket(response, response.length,packetIn.getAddress(),packetIn.getPort());
                socket.send(packetOut);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void start() {
    }

    @Override
    public int init() {
        try {
            socket = new DatagramSocket();
            return socket.getLocalPort();
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
