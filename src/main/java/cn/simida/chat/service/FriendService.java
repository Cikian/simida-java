package cn.simida.chat.service;


import cn.simida.chat.pojo.entity.Apply;
import cn.simida.chat.pojo.entity.Friend;
import cn.simida.common.pojo.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FriendService {
    List<User> getFriendsList(String uid);

    boolean add(Apply apply);

    List<User> getApply(String uid);

    @Transactional
    boolean agree(Friend friend);
}
