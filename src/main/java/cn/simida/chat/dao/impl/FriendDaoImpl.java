package cn.simida.chat.dao.impl;


import cn.simida.chat.dao.FriendDao;
import cn.simida.chat.mapper.FriendMapper;
import cn.simida.chat.pojo.entity.Friend;
import cn.simida.common.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
public class FriendDaoImpl implements FriendDao {
    @Autowired
    FriendMapper friendMapper;

    @Override
    public List<User> getFriendsList(String uid) {
        return friendMapper.getFriendsList(uid);
    }

    @Override
    public int add(Friend friend) {
        return friendMapper.insert(friend);
    }

    @Override
    public boolean getFriend(Friend friend) {
        QueryWrapper<Friend> qw = new QueryWrapper<>();
        qw.eq("uid", friend.getUid());
        qw.eq("fid", friend.getFid());
        Friend friend1 = friendMapper.selectOne(qw);
        return friend1 != null;
    }
}
