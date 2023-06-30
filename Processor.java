package clientserver;

import clientserver.database.Database;
import clientserver.message.ObjectDomain;
import clientserver.packet.DataCollector;
import clientserver.packet.Packet;
import clientserver.message.Message;
import clientserver.shop.Shop;
import clientserver.textcode.Decriptor;
import com.mkyong.hashing.*;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Processor {
    private static Decriptor decript;
    private static Receiver receive;

    public static String process(Packet packet) {
        byte[] receivedPacket = packet.getPack_in_bytes();
        byte[] decoded_message = new byte[receivedPacket.length - 16 - 8 - 2];
        System.arraycopy(receivedPacket, 24, decoded_message, 0, decoded_message.length);
        decoded_message = Decriptor.decrypt(decoded_message);
        byte[] decodedPacket = new byte[16 + 8 + decoded_message.length + 2];
        System.arraycopy(receivedPacket, 0, decodedPacket, 0, 16 + 8);
        for (int i = 0; i < decoded_message.length; i++) {
            decodedPacket[16 + 8 + i] = decoded_message[i];
        }
        decodedPacket[decodedPacket.length - 2] = receivedPacket[receivedPacket.length - 2];
        decodedPacket[decodedPacket.length - 1] = receivedPacket[receivedPacket.length - 1];
        int command = packet.getbMsg().getcType();
     //   Database.initialization(decoded_message, command);
        return Receiver.receivePacket();
    }

    public static Packet processedPacket(Packet packet) {
        byte[] receivedPacket = packet.getPack_in_bytes();
        byte[] decoded_message = new byte[receivedPacket.length - 16 - 8 - 2];
        System.arraycopy(receivedPacket, 24, decoded_message, 0, decoded_message.length);
        decoded_message = Decriptor.decrypt(decoded_message);
        byte[] decodedPacket = new byte[16 + 8 + decoded_message.length + 2];
        System.arraycopy(receivedPacket, 0, decodedPacket, 0, 16 + 8);
        for (int i = 0; i < decoded_message.length; i++) {
            decodedPacket[16 + 8 + i] = decoded_message[i];
        }
        decodedPacket[decodedPacket.length - 2] = receivedPacket[receivedPacket.length - 2];
        decodedPacket[decodedPacket.length - 1] = receivedPacket[receivedPacket.length - 1];
        ByteBuffer info = ByteBuffer.wrap(decodedPacket);
        return new Packet(DataCollector.getByteArray(info.getInt(16), info.getInt(20), info.get(1), info.getLong(2), new String(decoded_message)));
    }
}


