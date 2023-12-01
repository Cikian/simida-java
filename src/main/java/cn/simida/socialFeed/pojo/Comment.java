package cn.simida.socialFeed.pojo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Cikian
 * @version 1.0
 * @description TODO
 * @date 2023/11/14 22:13
 */
@Document(collection = "comments")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    private String commentId;
    private String feedId;
    private String userId;
    private String userAvatar;
    private String userNickName;
    private String toCommentId; // 评论的评论, 一级评论的commentId为0
    private String content;
    private String toUserId; // 评论的评论的接收者
    private String toUserNickName; // 评论的评论的接收者
    private String createTime;
    private String updateTime;
}
