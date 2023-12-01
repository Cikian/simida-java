package cn.simida.common.pojo;

import lombok.*;

/**
 * @author Cikian
 * @version 1.0
 * @description TODO
 * @date 2023/10/29 22:39
 */

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TextMail {
    private String to;
    private String subject;
    private String title;
    private String msg;
}
