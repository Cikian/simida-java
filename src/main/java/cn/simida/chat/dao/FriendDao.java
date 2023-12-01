package cn.simida.chat.dao;

import cn.simida.chat.pojo.entity.Friend;
import cn.simida.common.pojo.User;

import java.util.List;

public interface FriendDao {
    List<User> getFriendsList(String id);
    int add(Friend friend);

    boolean getFriend(Friend friend);
}
