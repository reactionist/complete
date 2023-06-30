package clientserver.protocol;

import clientserver.Processor;
import clientserver.packet.DataCollector;
import clientserver.packet.Packet;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class StoreServerTCPHandler {
    private Socket clientSocket;
    private DataOutputStream out;
    private DataInputStream in;

    public StoreServerTCPHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void start() throws IOException {
        out = new DataOutputStream(clientSocket.getOutputStream());
        in = new DataInputStream(clientSocket.getInputStream());
        int len = in.read();
        byte[] decoded_packet_in_bytes = new byte[len];
        in.read(decoded_packet_in_bytes);
        String answer = Processor.process(new Packet(decoded_packet_in_bytes));
        out.write(answer.getBytes(StandardCharsets.UTF_8));
        stop();
    }

    public void stop() throws IOException {
        clientSocket.close();
    }
}
