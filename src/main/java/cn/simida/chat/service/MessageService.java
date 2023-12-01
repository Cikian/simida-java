package cn.simida.chat.service;


import cn.simida.chat.pojo.entity.Message;
import cn.simida.common.pojo.User;

import java.util.Map;

public interface MessageService {

    Map<String,Object> list(User user, String to);

    boolean add(Message message);
}
