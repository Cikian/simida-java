package cn.simida.socialFeed.pojo.vo;

import lombok.*;

/**
 * @author Cikian
 * @version 1.0
 * @description TODO
 * @date 2023/11/13 10:39
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FollowingVo {
    private String id;
    private String following;  // 关注者ID
    private String followingName;
    private String followingAvatar;
    private String followingDesc;

}
