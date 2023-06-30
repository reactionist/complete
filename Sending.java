package clientserver;

import clientserver.textcode.Encriptor;
import clientserver.packet.Packet;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class Sending
{
    Encriptor encript;

    public static Packet sendPacket(byte[] bytes) {
        Packet pack = new Packet(bytes);

        byte[] encoded_message = Encriptor.encrypt(pack.getbMsg());

        ByteBuffer byteBuffer1 = ByteBuffer.allocate(8 + encoded_message.length);
        int new_wLen = 8 + encoded_message.length;
        byteBuffer1.putInt(pack.getbMsg().getcType());
        byteBuffer1.putInt(pack.getbMsg().getbUserId());

        for (byte encryptedMessageByte : encoded_message) {
            byteBuffer1.put(encryptedMessageByte);
        }
        byte[] encryptMessage = byteBuffer1.array();

        ByteBuffer byteBufferRes = ByteBuffer.allocate(18 + encryptMessage.length);

        ByteBuffer byteBuffer = ByteBuffer.allocate(14);
        byteBuffer.put(pack.getbMagic());
        byteBuffer.put(pack.getbSrc());
        byteBuffer.putLong(pack.getbPkId());
        byteBuffer.putInt(new_wLen);
        for (byte b : byteBuffer.array()) {
            byteBufferRes.put(b);
        }
        byteBufferRes.putShort(pack.getwCrc16());
        for (byte b : encryptMessage) {
            byteBufferRes.put(b);
        }
        byteBufferRes.putShort(pack.getwCrc16_2());

        pack.rewritePacket(byteBufferRes.array());
        return pack;
    }
}
