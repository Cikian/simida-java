package cn.simida.chat.pojo.vo;

import lombok.*;

/**
 * @author Cikian
 * @version 1.0
 * @description TODO
 * @date 2023/10/22 15:48
 */

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MesVO {
    private String id;
    private String fromId;
    private String toId;
    private Integer type;
    private String content;
    private String time;
    private String avatar;
}
