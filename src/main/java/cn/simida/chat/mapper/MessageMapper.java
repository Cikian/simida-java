package cn.simida.chat.mapper;

import cn.simida.chat.pojo.entity.Message;
import cn.simida.chat.pojo.vo.MesVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MessageMapper extends BaseMapper<Message> {

    @Select("SELECT a.* FROM ( SELECT a.*,b.avatar from message as `a` LEFT JOIN `user` as `b` ON a.`from_id` = b.user_id WHERE `from_id` = #{from} and `to_id` = #{to} UNION SELECT a.*,b.avatar from message as `a` LEFT JOIN `user` as `b` ON a.`from_id` = b.user_id WHERE `from_id` = #{to} and `to_id` = #{from} ) as `a` ORDER BY a.time asc")
    List<MesVO> list(@Param("from")String id, @Param("to")String to);
}
