package cn.simida.socialFeed.dao.impl;

import cn.simida.socialFeed.dao.LikeDao;
import cn.simida.socialFeed.mapper.LikeMapper;
import cn.simida.socialFeed.pojo.Like;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Cikian
 * @version 1.0
 * @description TODO
 * @date 2023/11/14 18:45
 */

@Repository
public class LikeDaoImpl implements LikeDao {
    @Autowired
    LikeMapper likeMapper;

    @Override
    public boolean addLike(Like like) {
        return likeMapper.insert(like) > 0;
    }

    @Override
    public boolean deleteLike(String feedId, String userId) {
        QueryWrapper<Like> qw = new QueryWrapper<>();
        qw.eq("feed_id", feedId);
        qw.eq("user_id", userId);
        return likeMapper.delete(qw) > 0;
    }

    @Override
    public Like getLike(String feedId, String userId) {
        QueryWrapper<Like> qw = new QueryWrapper<>();
        qw.eq("feed_id", feedId);
        qw.eq("user_id", userId);
        return likeMapper.selectOne(qw);
    }

    @Override
    public List<Like> getLikesByFeedId(String feedId) {
        QueryWrapper<Like> qw = new QueryWrapper<>();
        qw.eq("feed_id", feedId);
        return likeMapper.selectList(qw);
    }

    @Override
    public List<Like> getLikesByUserId(String userId) {
        QueryWrapper<Like> qw = new QueryWrapper<>();
        qw.eq("user_id", userId);
        return likeMapper.selectList(qw);
    }
}
