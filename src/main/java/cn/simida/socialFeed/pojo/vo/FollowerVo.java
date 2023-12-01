package cn.simida.socialFeed.pojo.vo;

import lombok.*;

/**
 * @author Cikian
 * @version 1.0
 * @description TODO
 * @date 2023/11/13 10:38
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FollowerVo {
    private String id;
    private String follower;  // 粉丝ID
    private String followerName;
    private String followerAvatar;
    private String followerDesc;
}
