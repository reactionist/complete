package clientserver.message;
import java.nio.ByteBuffer;
public class Message {
    private int cType;
    private int bUserId;
    private byte[] message;


    public Message(ByteBuffer byteBuffer, int len)
    {
        this.cType=byteBuffer.getInt(0);
        this.bUserId=byteBuffer.getInt(4);
        this.message=new byte[len];
        for (int i = 0; i < this.message.length; ++i) {
            this.message[i] = byteBuffer.get(8 + i);
        }
    }


    public int getcType(){
        return cType;
    }

    public int getbUserId(){
        return bUserId;
    }

    public byte[] getMessage() {
        return message;
    }

    public void setMessage(byte[] message){
        this.message = message;
    }

}