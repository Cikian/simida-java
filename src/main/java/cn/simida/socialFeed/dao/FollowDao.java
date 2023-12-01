package cn.simida.socialFeed.dao;

import cn.simida.socialFeed.pojo.Follow;

import java.util.List;

public interface FollowDao {
    List<Follow> getFollowers(String userId);

    List<Follow> getFollowings(String userId);

    Follow getFollowByFollowerIdAndFollowingId(String follower, String following);

    boolean addFollowing(Follow follow);

    boolean unFollowing(String uid, String followingId);

    List<String> getRecommendUserId(String userId);

    List<String> getFollowedUserIds(String userId);
}
