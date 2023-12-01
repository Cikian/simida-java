package cn.simida.chat.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("apply")
public class Apply {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String uid;
    private String tid;
}



