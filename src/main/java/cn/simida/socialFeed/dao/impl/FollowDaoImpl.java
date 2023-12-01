package cn.simida.socialFeed.dao.impl;

import cn.simida.socialFeed.dao.FollowDao;
import cn.simida.socialFeed.mapper.FollowMapper;
import cn.simida.socialFeed.pojo.Follow;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Cikian
 * @version 1.0
 * @description TODO
 * @date 2023/11/13 10:32
 */

@Repository
public class FollowDaoImpl implements FollowDao {
    @Autowired
    FollowMapper followMapper;

    @Override
    public List<Follow> getFollowers(String userId) {
        QueryWrapper<Follow> qw = new QueryWrapper<>();
        qw.eq("following", userId);
        return followMapper.selectList(qw);
    }

    @Override
    public List<Follow> getFollowings(String userId) {
        QueryWrapper<Follow> qw = new QueryWrapper<>();
        qw.eq("follower", userId);
        return followMapper.selectList(qw);
    }

    @Override
    public Follow getFollowByFollowerIdAndFollowingId(String follower, String following) {
        QueryWrapper<Follow> qw = new QueryWrapper<>();
        qw.eq("follower", follower);
        qw.eq("following", following);
        return followMapper.selectOne(qw);
    }

    @Override
    public boolean addFollowing(Follow follow) {
        return followMapper.insert(follow) > 0;
    }

    @Override
    public boolean unFollowing(String uid, String followingId) {
        QueryWrapper<Follow> qw = new QueryWrapper<>();
        qw.eq("follower", uid);
        qw.eq("following", followingId);
        return followMapper.delete(qw) > 0;
    }

    @Override
    public List<String> getRecommendUserId(String userId) {
        List<String> followedUserIds = getFollowedUserIds(userId);
        followedUserIds.add(userId);
        System.out.println("followedUserIds = " + followedUserIds);
        int limit = 5;
        List<String> recommendedUserIds = null;
        // 获取推荐用户ID，排除已关注的用户
        recommendedUserIds = followMapper.selectRecommendedUserIds(String.join(",", followedUserIds), limit);
        return recommendedUserIds;
    }

    @Override
    public List<String> getFollowedUserIds(String userId) {
        return followMapper.selectList(new QueryWrapper<Follow>().eq("follower", userId))
                .stream().map(Follow::getFollowing).collect(Collectors.toList());
    }
}
