package cn.simida.utils;

import org.springframework.stereotype.Component;

/**
 * @author Cikian
 * @version 1.0
 * @description TODO
 * @date 2023/10/31 9:52
 */

@Component
public class VerificationCodeUtils {
    /**
     * @param count 验证码位数
     * @return 验证码
     */
    public static String generatedCode(int count) {
        return String.valueOf((int) ((Math.random() * 9 + 1) * Math.pow(10, count - 1)));
    }

}
