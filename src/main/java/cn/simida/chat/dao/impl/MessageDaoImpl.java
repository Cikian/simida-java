package cn.simida.chat.dao.impl;

import cn.simida.chat.dao.MessageDao;
import cn.simida.chat.mapper.MessageMapper;
import cn.simida.chat.pojo.entity.Message;
import cn.simida.chat.pojo.vo.MesVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Cikian
 * @version 1.0
 * @description TODO
 * @date 2023/10/17 15:56
 */

@Repository
public class MessageDaoImpl implements MessageDao {
    @Autowired
    MessageMapper messageMapper;
    @Override
    public List<MesVO> list(String id, String to) {
        List<MesVO> list = messageMapper.list(id, to);
        System.out.println("messageMapper---list = " + list);
        return list;
    }

    @Override
    public int add(Message message) {
        System.out.println("****************messageMapper---message = " + message);
        return messageMapper.insert(message);
    }
}
