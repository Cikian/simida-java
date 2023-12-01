package cn.simida.socialFeed.pojo.vo;

import cn.simida.socialFeed.pojo.Comment;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * @author Cikian
 * @version 1.0
 * @description TODO
 * @date 2023/11/14 22:11
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FeedVo {
    @Id
    private String feedId;
    private String userId;
    private String content;
    private String imageUrl;
    private String createTime;
    private String updateTime;

    private String userNickName;
    private String userAvatar;
    private String userEmail;

    private List<String> likedUserIds;
    private List<Comment> comments;
}
