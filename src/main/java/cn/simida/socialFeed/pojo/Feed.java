package cn.simida.socialFeed.pojo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Cikian
 * @version 1.0
 * @description TODO
 * @date 2023/11/2 16:25
 */

@Document(collection = "socialfeed")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Feed {
    @Id
    private String feedId;
    private String userId;
    private String content;
    private String imageUrl;
    private String createTime;
    private String updateTime;
    private String userNickName;
    private String userAvatar;

}
