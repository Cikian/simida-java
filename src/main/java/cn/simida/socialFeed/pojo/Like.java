package cn.simida.socialFeed.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * @author Cikian
 * @version 1.0
 * @description TODO
 * @date 2023/11/14 18:46
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("liked")
public class Like {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String feedId;
    private String userId;
}
