package cn.simida.socialFeed.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * @author Cikian
 * @version 1.0
 * @description TODO
 * @date 2023/11/13 10:24
 */

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FollowVo {
    private String id;
    private String follower;
    private String following;
    private String followerName;
    private String followingName;
    private String followerAvatar;
    private String followingAvatar;

}
