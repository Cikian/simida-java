package cn.simida.chat.mapper;


import cn.simida.chat.pojo.entity.Apply;
import cn.simida.common.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ApplyMapper extends BaseMapper<Apply> {
    @Select("SELECT b.* FROM apply as `a` LEFT JOIN `user` as `b` on a.uid = b.user_id WHERE a.tid = #{id}")
    List<User> getApply(String uid);
}
