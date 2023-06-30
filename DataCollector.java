package clientserver.packet;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class DataCollector {
    public static byte[] getByteArray(int cType, int bUserId, byte bSrc, long bPkId, String query){
        ByteBuffer byteBuffer_msg = ByteBuffer.allocate(8 + query.length());
        byteBuffer_msg.putInt(cType).putInt(bUserId).put(query.getBytes(StandardCharsets.UTF_8));

        ByteBuffer byteBuffer_pck = ByteBuffer.allocate(8 + query.length() + 18);
        ByteBuffer byteBuffer_pck14 = ByteBuffer.allocate(14);
        byteBuffer_pck14.put((byte) 0x13).put(bSrc).putLong(bPkId).putInt(8 + query.length());
        for (byte b : byteBuffer_pck14.array()) {
            byteBuffer_pck.put(b);
        }
        short crc1 = CRC16.getCRC(byteBuffer_pck14.array());
        short crc2 = CRC16.getCRC(byteBuffer_msg.array());
        byteBuffer_pck.putShort(crc1);
        for (byte b : byteBuffer_msg.array()) {
            byteBuffer_pck.put(b);
        }
        byteBuffer_pck.putShort(crc2);
        return byteBuffer_pck.array();
    }
}
