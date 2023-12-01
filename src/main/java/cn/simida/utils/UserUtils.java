package cn.simida.utils;

import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * @author Cikian
 * @version 1.0
 * @description TODO
 * @date 2023/10/21 21:40
 */
public class UserUtils {
    // 校验密码
    public static boolean checkPassword(String uPassword, String dbPassword) {
        uPassword += "cikian";
        uPassword = DigestUtils.md5DigestAsHex(uPassword.getBytes(StandardCharsets.UTF_8));
        return uPassword.equals(dbPassword);
    }
}
