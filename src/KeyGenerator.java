import java.security.SecureRandom;

public class KeyGenerator {
    public static byte[] generateKey() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] key = new byte[32];
        secureRandom.nextBytes(key);
        return key;
    }
}
