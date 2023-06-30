package clientserver.textcode;

import clientserver.message.Message;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Encriptor {

    public static byte[] encrypt(Message message)
    {
        byte[] ms = message.getMessage();
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            String key = "ABCDEFGHIJKLLLLL";
            byte[] encryptKeyBytes = key.getBytes();
            SecretKey secretKey = new SecretKeySpec(encryptKeyBytes, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            ms = cipher.doFinal(ms);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException |
                 BadPaddingException e) {
            e.printStackTrace();
        }
        return ms;
    }
}