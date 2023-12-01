package cn.simida.socialFeed.service;

import cn.simida.socialFeed.pojo.vo.FollowVo;
import cn.simida.socialFeed.pojo.vo.FollowerVo;
import cn.simida.socialFeed.pojo.vo.FollowingVo;

import java.util.List;

public interface FollowService {
    List<FollowerVo> getFollowers(String userId);
    List<FollowingVo> getFollowings(String userId);

    boolean addFollowing(String uid, String followingId);
    boolean unFollowing(String uid, String followingId);
}
