package cn.simida.socialFeed.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * @author Cikian
 * @version 1.0
 * @description TODO
 * @date 2023/11/13 10:22
 */

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("follow")
public class Follow {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String follower;
    private String following;

}
