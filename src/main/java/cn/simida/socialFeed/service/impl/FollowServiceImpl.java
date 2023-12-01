package cn.simida.socialFeed.service.impl;

import cn.simida.chat.pojo.entity.Friend;
import cn.simida.chat.service.FriendService;
import cn.simida.common.dao.UserDao;
import cn.simida.common.pojo.User;
import cn.simida.socialFeed.dao.FollowDao;
import cn.simida.socialFeed.pojo.Follow;
import cn.simida.socialFeed.pojo.vo.FollowerVo;
import cn.simida.socialFeed.pojo.vo.FollowingVo;
import cn.simida.socialFeed.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Cikian
 * @version 1.0
 * @description TODO
 * @date 2023/11/13 10:37
 */
@Service
public class FollowServiceImpl implements FollowService {
    @Autowired
    FollowDao followDao;
    @Autowired
    UserDao userDao;
    @Autowired
    FriendService friendService;

    @Override
    public List<FollowerVo> getFollowers(String userId) {
        List<Follow> followers = followDao.getFollowers(userId);
        List<FollowerVo> followerVos = new LinkedList<>();
        for (Follow follower : followers) {
            User followerUser = userDao.getById(follower.getFollower());
            FollowerVo followerVo = FollowerVo.builder()
                    .id(follower.getId())
                    .follower(follower.getFollower())
                    .followerName(followerUser.getNickName())
                    .followerAvatar(followerUser.getAvatar())
                    .followerDesc(followerUser.getDescription())
                    .build();
            followerVos.add(followerVo);
        }

        return followerVos;
    }

    @Override
    public List<FollowingVo> getFollowings(String userId) {
        List<Follow> followings = followDao.getFollowings(userId);
        List<FollowingVo> followingVos = new LinkedList<>();
        for (Follow following : followings) {
            User followingUser = userDao.getById(following.getFollowing());
            FollowingVo followingVo = FollowingVo.builder()
                    .id(following.getId())
                    .following(following.getFollowing())
                    .followingName(followingUser.getNickName())
                    .followingAvatar(followingUser.getAvatar())
                    .followingDesc(followingUser.getDescription())
                    .build();
            followingVos.add(followingVo);
        }
        return followingVos;
    }


    @Caching(
            evict = {
                    @CacheEvict(value = "user", key = "#uid"),
                    @CacheEvict(value = "user", key = "#followingId")
            }
    )
    @Override
    public boolean addFollowing(String uid, String followingId) {
        Follow followed = followDao.getFollowByFollowerIdAndFollowingId(uid, followingId);
        if (followed != null){
            return false;
        }
        Follow follow = new Follow();
        follow.setFollower(uid);
        follow.setFollowing(followingId);
        boolean followedFlag = followDao.addFollowing(follow);
        boolean addFriendFlag = true;
        if (followedFlag) {
            if (isFollowing(followingId, uid)) {
                Friend friend = new Friend();
                friend.setUid(uid);
                friend.setFid(followingId);
                addFriendFlag = friendService.agree(friend);
            }
        }
        return followedFlag && addFriendFlag;
    }

    public boolean isFollowing(String uid, String followingId) {
        Follow followed = followDao.getFollowByFollowerIdAndFollowingId(uid, followingId);
        return followed != null;
    }

    @Override
    public boolean unFollowing(String uid, String followingId) {
        return followDao.unFollowing(uid,followingId);
    }
}
