package clientserver.packet;

import clientserver.message.Message;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class Packet {
    private byte bMagic = 0x13;
    private byte bSrc;
    private long bPkId;
    private int wLen;
    private short wCrc16;
    private Message bMsg;
    private short wCrc16_2;
    private byte[] pack_in_bytes;

    public Packet(byte[] bytes){
        if (bytes[0] != 0x13) {
            throw new IllegalArgumentException("Bad bMagic!!!");
        }
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        this.bMagic = buffer.get(0);
        this.bSrc = buffer.get(1);
        this.bPkId = buffer.getLong(2);
        this.wLen = buffer.getInt(10);
        this.wCrc16 = buffer.getShort(14);
        if (wCrc16 != wCrc16()){
            throw new IllegalArgumentException("Wrong crc1");
        }
        ByteBuffer buffer_msg = ByteBuffer.allocate(wLen);
        buffer_msg.put(bytes, 16, wLen);
        this.bMsg = new Message(buffer_msg, this.wLen - 8);
        this.wCrc16_2 = buffer.getShort(16+wLen);
        if (wCrc16_2 != wCrc16_2()) {
            throw new IllegalArgumentException("Wrong crc2");
        }
        this.pack_in_bytes = bytes;
    }

    public byte getbMagic(){
        return bMagic;
    }

    public byte getbSrc() {
        return bSrc;
    }

    public long getbPkId() {
        return bPkId;
    }

    public int getwLen(){
        return wLen;
    }

    public short getwCrc16(){
        return wCrc16;
    }

    public Message getbMsg() {
        return bMsg;
    }

    public short getwCrc16_2(){
        return wCrc16_2;
    }

    public short wCrc16()
    {
        ByteBuffer byteBuffer = ByteBuffer.allocate(14);
        byteBuffer.put(bMagic);
        byteBuffer.put(bSrc);
        byteBuffer.putLong(bPkId);
        byteBuffer.putInt(wLen);
        byte[] bb = byteBuffer.array();
        return CRC16.getCRC(bb);
    }

    public short wCrc16_2()
    {
        ByteBuffer byteBuffer3 = ByteBuffer.allocate(8+bMsg.getMessage().length);
        byteBuffer3.putInt(bMsg.getcType()).putInt(bMsg.getbUserId()).put(bMsg.getMessage());
        return CRC16.getCRC(byteBuffer3.array());
    }

    public byte[] getPack_in_bytes(){
        return pack_in_bytes;
    }

    public void rewritePacket(byte[] bytes){
        byte[] bytes_first14 = new byte[14];
        System.arraycopy(bytes,0,bytes_first14,0,14);
        short wCrc16_changed = CRC16.getCRC(bytes_first14);
        if (bytes[0] != 0x13) {
            throw new IllegalArgumentException("Bad bMagic!!!");
        }
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        this.bSrc = buffer.get(1);
        this.bPkId = buffer.getLong(2);
        this.wLen = bytes.length-2 - 16;
        this.wCrc16 = wCrc16_changed;
        ByteBuffer buffer_msg = ByteBuffer.allocate(this.wLen);
        buffer_msg.put(bytes, 16, this.wLen);
        this.bMsg = new Message(buffer_msg, this.wLen - 8);
        this.wCrc16_2 = CRC16.getCRC(buffer_msg.array());
        ByteBuffer newbytebuffer = ByteBuffer.allocate(16+2+this.wLen);
        newbytebuffer.put((byte)0x13).put(this.bSrc).putLong(this.bPkId).putInt(this.wLen).putShort(this.wCrc16);
        for (byte b : buffer_msg.array()) {
            newbytebuffer.put(b);
        }
        newbytebuffer.putShort(this.wCrc16_2);
        this.pack_in_bytes = newbytebuffer.array();
    }
}