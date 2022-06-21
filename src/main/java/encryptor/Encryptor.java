package encryptor;

import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.DESEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.modes.CFBBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.encoders.Base64;

import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

public class Encryptor {

    final static Logger log = Logger.getLogger(String.valueOf(Encryptor.class));

    public static final String KEY = "PASS";

    private final BufferedBlockCipher cipher;
    private final KeyParameter key;

    public Encryptor(byte[] key) {
        cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(new DESEngine()));
        this.key = new KeyParameter(key);
    }

    public Encryptor(String key){
        this(key.getBytes());
    }

    private byte[] callCipher(byte[] data) throws InvalidCipherTextException {
        int size = cipher.getOutputSize(data.length);
        byte[] result = new byte[size];
        int olen = cipher.processBytes(data, 0, data.length, result, 0);
        olen += cipher.doFinal(result, olen);
        if(olen < size){
            byte[] tmp = new byte[olen];
            System.arraycopy(result, 0, tmp, 0, olen);
            result = tmp;
        }
        return result;
    }

    public synchronized byte[] encrypt(byte[] data) throws InvalidCipherTextException {
        if(data == null || data.length == 0){
            return new byte[0];
        }
        cipher.init(true, key);
        return callCipher(data);
    }

    public byte[] encryptString(String data) throws InvalidCipherTextException{
        if(data == null || data.length() == 0){
            return new byte[0];
        }
        return encrypt(data.getBytes());
    }

    public String encryptStringEncoded(String data){
        String dataString = null;
        try{
            byte[] dataEncrypt = encryptString(data);
            dataString = new  String(Base64.encode(dataEncrypt));
        } catch (InvalidCipherTextException e) {
            log.info("Error encoding: " + e.getMessage());
        }
        return dataString;
    }

    public synchronized byte[] decrypt(byte[] data) throws InvalidCipherTextException {
        if(data == null || data.length == 0){
            return new byte[0];
        }
        cipher.init(false, key);
        return callCipher(data);
    }

    public String decryptStringEncoded(String data){
        String decodedString = null;
        try{
        byte[] decodedData = Base64.decode(data);

        if(decodedData.length == 0){
            return "";
        }
            decodedString = new  String(decrypt(decodedData));
        } catch(InvalidCipherTextException e){
                log.info("Error decrypting");
        }
        return decodedString;
    }

    public String decryptString(byte[] data) throws InvalidCipherTextException {
        if(data == null || data.length == 0){
            return "";
        }
        return new String(decrypt(data));
    }

    public static void main(String[] args) {
        String testDate = "benjamin";
        Encryptor encryptor = new Encryptor(Encryptor.KEY);
        String encodedData = encryptor.encryptStringEncoded(testDate);
        System.out.println("encrypted data -> " + encodedData);

        System.out.println("Decrypted data: " + encryptor.decryptStringEncoded(encodedData));
    }



}
