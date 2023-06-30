package clientserver.protocol;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class StoreServerUDP implements Runnable {

    private DatagramSocket socket;
    private boolean running;
    private byte[] buf = new byte[256];

    public void start(int port) {
        try {
            socket = new DatagramSocket(port);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            try {
                new StoreServerUDPHandler(socket).start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void run() {
        StoreServerUDP server = new StoreServerUDP();
        server.start(4445);
    }
}