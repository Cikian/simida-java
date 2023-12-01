package cn.simida.common.pojo.vo;

import cn.simida.common.pojo.User;
import lombok.*;

/**
 * @author Cikian
 * @version 1.0
 * @description TODO
 * @date 2023/11/10 3:04
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RegisterVo {
    private User user;
    private String verificationCode;
}
