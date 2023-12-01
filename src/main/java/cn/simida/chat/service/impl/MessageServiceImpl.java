package cn.simida.chat.service.impl;


import cn.simida.chat.dao.MessageDao;
import cn.simida.chat.pojo.entity.Message;
import cn.simida.common.pojo.User;
import cn.simida.chat.pojo.vo.MesVO;
import cn.simida.chat.service.MessageService;
import cn.simida.utils.TimeUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;

    public Map<String,Object> list(User user, String to) {
        List<MesVO> mesList = messageDao.list(user.getUserId(), to);
        System.out.println("MessageServiceImpl---list = " + mesList);
        Map<String,Object> map = new HashMap<>();
        map.put("mesList",mesList);
        map.put("total", PageInfo.of(mesList).getTotal());
        System.out.println("MessageServiceImpl---map = " + map);
        return map;
    }

    public boolean add(Message message) {
        message.setType(1);
        String nowTime = TimeUtils.getNowTime();
        message.setTime(nowTime);
        return messageDao.add(message) > 0;
    }


}
