package cn.simida.chat.mapper;

import cn.simida.chat.pojo.entity.Friend;
import cn.simida.common.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FriendMapper extends BaseMapper<Friend> {
    @Select("select b.* from friend as `a` JOIN user as `b` ON a.fid = b.user_id where a.uid = #{id}")
    List<User> getFriendsList(String id);
}
