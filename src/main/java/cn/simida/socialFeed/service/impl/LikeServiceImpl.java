package cn.simida.socialFeed.service.impl;

import cn.simida.socialFeed.dao.LikeDao;
import cn.simida.socialFeed.pojo.Like;
import cn.simida.socialFeed.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Cikian
 * @version 1.0
 * @description TODO
 * @date 2023/11/14 18:48
 */
@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    LikeDao likeDao;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = "feed", key = "#feedId"),
                    @CacheEvict(value = "user_feed", key = "#feedUserId", allEntries = true),
                    @CacheEvict(value = "page", allEntries = true)
            }
    )

    public boolean addLike(String feedId, String userId, String feedUserId) {
        Like liked = likeDao.getLike(feedId, userId);
        if (liked != null) {
            return false;
        }
        Like like = new Like();
        like.setFeedId(feedId);
        like.setUserId(userId);
        return likeDao.addLike(like);
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = "feed", key = "#feedId"),
                    @CacheEvict(value = "user_feed", key = "#feedUserId", allEntries = true),
                    @CacheEvict(value = "page", allEntries = true)
            }
    )
    public boolean deleteLike(String feedId, String userId, String feedUserId) {
        Like liked = likeDao.getLike(feedId, userId);
        if (liked == null) {
            return false;
        }
        return likeDao.deleteLike(feedId, userId);
    }

    @Override
    public List<String> getLikesByFeedId(String feedId) {
        List<Like> likesByFeedId = likeDao.getLikesByFeedId(feedId);
        return likesByFeedId.stream().map(Like::getUserId).collect(java.util.stream.Collectors.toList());
    }

    @Override
    public List<String> getLikesByUserId(String userId) {
        List<Like> likesByUserId = likeDao.getLikesByUserId(userId);
        return likesByUserId.stream().map(Like::getFeedId).collect(java.util.stream.Collectors.toList());
    }
}
