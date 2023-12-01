package cn.simida.chat.service.impl;

import cn.simida.chat.dao.ApplyDao;
import cn.simida.chat.dao.FriendDao;
import cn.simida.chat.pojo.entity.Apply;
import cn.simida.chat.pojo.entity.Friend;
import cn.simida.chat.service.FriendService;
import cn.simida.common.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    private FriendDao friendDao;
    @Autowired
    private ApplyDao applyDao;

    public List<User> getFriendsList(String uid) {
        return friendDao.getFriendsList(uid);
    }


    public boolean add(Apply apply) {
        return applyDao.add(apply) > 0;
    }

    public List<User> getApply(String uid) {
        return applyDao.getApply(uid);
    }


    public boolean agree(Friend friend) {
        boolean flag1 = true;
        boolean flag2 = true;
        // 如果已经是好友了，就不再添加
        // 当前用户添加好友
        if (!friendDao.getFriend(friend)) {
            flag1 = friendDao.add(friend) > 0;
        }

        // 好友添加当前用户
        Friend friend1 = new Friend();
        friend1.setUid(friend.getFid());
        friend1.setFid(friend.getUid());
        if (!friendDao.getFriend(friend1)) {
            flag2 = friendDao.add(friend1) > 0;
        }

        return flag1 && flag2;
    }
}
