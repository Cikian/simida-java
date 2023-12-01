package cn.simida.chat.dao;

import cn.simida.chat.pojo.entity.Message;
import cn.simida.chat.pojo.vo.MesVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageDao {
    List<MesVO> list(@Param("from")String id, @Param("to")String to);

    int add(Message message);
}
