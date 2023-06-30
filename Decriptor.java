package clientserver.textcode;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Decriptor {

    public static byte[] decrypt(byte[] message) {
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            String key = "ABCDEFGHIJKLLLLL";
            byte[] decryptKeyBytes = key.getBytes();
            SecretKey secretKey = new SecretKeySpec(decryptKeyBytes, "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            message = cipher.doFinal(message);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return message;
    }
}
