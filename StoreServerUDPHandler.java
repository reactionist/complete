package clientserver.protocol;

import clientserver.Processor;
import clientserver.packet.Packet;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class StoreServerUDPHandler {
    private DatagramSocket socket;
    private boolean running;
    private byte[] buf = new byte[256];

    public StoreServerUDPHandler(DatagramSocket socket) {
        this.socket = socket;
    }

    public void start() throws IOException {
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        InetAddress address = packet.getAddress();
        int port = packet.getPort();
        ByteBuffer bb = ByteBuffer.wrap(buf);
        int len = 14 + 2 + bb.getInt(10) + 2;
        byte[] buf_full = new byte[len];
        System.arraycopy(packet.getData(), 0, buf_full, 0, len);
        Packet new_pack = Processor.processedPacket(new Packet(buf_full));
        packet = new DatagramPacket(new_pack.getPack_in_bytes(), new_pack.getPack_in_bytes().length, address, port);
        socket.send(packet);
    }

    public void stop() throws IOException {
        socket.close();
    }
}
