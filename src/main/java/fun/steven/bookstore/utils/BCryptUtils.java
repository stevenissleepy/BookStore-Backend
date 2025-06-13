package fun.steven.bookstore.utils;

import java.security.MessageDigest;
import java.util.Base64;

import org.mindrot.jbcrypt.BCrypt;

public class BCryptUtils {
    private static String gensalt(String username) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(username.getBytes("UTF-8"));
            String base64Hash = Base64.getEncoder().encodeToString(hash); // 转换为 BCrypt 兼容的 salt 格式
            String saltPart = base64Hash.substring(0, 22); // 取前22个字符作为 salt（BCrypt salt 长度）

            return "$2a$10$" + saltPart; // 构造 BCrypt salt 格式
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String hashPassword(String password, String username) {
        String salt = gensalt(username);
        return BCrypt.hashpw(password, salt);
    }
}
