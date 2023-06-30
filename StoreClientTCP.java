package clientserver.protocol;

import clientserver.Sending;
import clientserver.packet.Packet;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class StoreClientTCP {
    private Socket clientSocket;
    private DataOutputStream out;
    private DataInputStream in;

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new DataOutputStream(clientSocket.getOutputStream());
        in = new DataInputStream(clientSocket.getInputStream());
    }

    public void sendPacket(Packet packet) throws IOException {
        byte[] pack_in_bytes = packet.getPack_in_bytes();
        Packet encoded_packet = Sending.sendPacket(pack_in_bytes);
        byte[] encoded_pack_in_bytes = encoded_packet.getPack_in_bytes();
        out.write(encoded_pack_in_bytes.length);
        out.write(encoded_pack_in_bytes);
        out.flush();
    }

    public String readPacket() throws IOException {
        return new String(in.readNBytes(2));
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }
}