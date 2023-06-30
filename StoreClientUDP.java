package clientserver.protocol;

import clientserver.Receiver;
import clientserver.Sending;
import clientserver.packet.Packet;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;

public class StoreClientUDP {
    private DatagramSocket socket;
    private InetAddress address;

    private byte[] buf;

    public StoreClientUDP() {
        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
        try {
            address = InetAddress.getByName("localhost");
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    public String sendPacket(Packet p) {
        byte[] buf = p.getPack_in_bytes();
        Packet pack = Sending.sendPacket(p.getPack_in_bytes());
        DatagramPacket packet
                = new DatagramPacket(pack.getPack_in_bytes(), pack.getPack_in_bytes().length, address, 4445);
        try {
            socket.send(packet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        byte[] buf_new = new byte[buf.length];
        packet = new DatagramPacket(buf_new, buf_new.length);
        try {
            socket.receive(packet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (Arrays.equals(buf, buf_new)) return Receiver.receivePacket();
        else return "Wrong";
    }

    public void close() {
        socket.close();
    }
}