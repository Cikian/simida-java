package cn.simida.chat.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import static com.baomidou.mybatisplus.annotation.IdType.ASSIGN_ID;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("friend")
public class Friend {
    @TableId(type = ASSIGN_ID)
    private String id;
    private String uid;
    private String fid;

}
