package cn.simida.socialFeed.service.impl;

import cn.simida.common.dao.UserDao;
import cn.simida.common.pojo.User;
import cn.simida.socialFeed.dao.FeedDao;
import cn.simida.socialFeed.dao.FollowDao;
import cn.simida.socialFeed.pojo.Comment;
import cn.simida.socialFeed.pojo.Feed;
import cn.simida.socialFeed.pojo.vo.FeedVo;
import cn.simida.socialFeed.service.CommentService;
import cn.simida.socialFeed.service.FeedService;
import cn.simida.socialFeed.service.LikeService;
import cn.simida.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Cikian
 * @version 1.0
 * @description TODO
 * @date 2023/11/2 16:29
 */
@Service
public class FeedServiceImpl implements FeedService {
    @Autowired
    FeedDao feedDao;
    @Autowired
    UserDao userDao;
    @Autowired
    LikeService likeService;
    @Autowired
    CommentService commentService;
    @Autowired
    FollowDao followDao;

    @Caching(
            put = @CachePut(value = "feed", key = "#feed.feedId"),
            evict = {
                    @CacheEvict(value = "feed", key = "'allFeed'"),
                    @CacheEvict(value = "page", allEntries = true),
                    @CacheEvict(value = "user_feed", key = "#feed.userId")
            }
    )
    @Override
    public FeedVo addFeed(Feed feed, User user) {
        String nowTime = TimeUtils.getNowTime();
        feed.setCreateTime(nowTime);
        feed.setUpdateTime(nowTime);
        feed.setUserId(user.getUserId());
        feed.setUserAvatar(user.getAvatar());
        feed.setUserNickName(user.getNickName());
        Feed savedFeed = feedDao.addFeed(feed);
        return this.getFeedById(savedFeed.getFeedId());
    }

    @Cacheable(value = "feed", key = "'allFeed'")
    @Override
    public List<FeedVo> getAllFeed() {
        List<Feed> allFeed = feedDao.getAllFeed();
        List<FeedVo> feedVos = new LinkedList<>();
        for (Feed feed : allFeed) {
            User user = userDao.getById(feed.getUserId());
            FeedVo feedVo = new FeedVo();
            List<String> likesByFeedId = likeService.getLikesByFeedId(feed.getFeedId());
            List<Comment> commentsByFeedId = commentService.getCommentByFeedId(feed.getFeedId());
            feedVo.setFeedId(feed.getFeedId());
            feedVo.setUserId(feed.getUserId());
            feedVo.setContent(feed.getContent());
            feedVo.setImageUrl(feed.getImageUrl());
            feedVo.setCreateTime(feed.getCreateTime());
            feedVo.setUpdateTime(feed.getUpdateTime());
            feedVo.setUserAvatar(user.getAvatar());
            feedVo.setUserNickName(user.getNickName());
            feedVo.setUserEmail(user.getEmail());
            feedVo.setLikedUserIds(likesByFeedId);
            feedVo.setComments(commentsByFeedId);
            feedVos.add(feedVo);
        }
        return feedVos;
    }

    @Cacheable(value = "feed", key = "#feedId")
    @Override
    public FeedVo getFeedById(String feedId) {
        Feed feedById = feedDao.getFeedById(feedId);
        User user = userDao.getById(feedById.getUserId());
        List<String> likesByFeedId = likeService.getLikesByFeedId(feedById.getFeedId());
        List<Comment> commentsByFeedId = commentService.getCommentByFeedId(feedById.getFeedId());
        FeedVo feedVo = new FeedVo();
        feedVo.setFeedId(feedById.getFeedId());
        feedVo.setUserId(feedById.getUserId());
        feedVo.setContent(feedById.getContent());
        feedVo.setImageUrl(feedById.getImageUrl());
        feedVo.setCreateTime(feedById.getCreateTime());
        feedVo.setUpdateTime(feedById.getUpdateTime());
        feedVo.setUserAvatar(user.getAvatar());
        feedVo.setUserNickName(user.getNickName());
        feedVo.setUserEmail(user.getEmail());
        feedVo.setLikedUserIds(likesByFeedId);
        feedVo.setComments(commentsByFeedId);
        return feedVo;
    }

    @Caching(
            evict = {
                    @CacheEvict(value = "feed", key = "#feedId"),
                    @CacheEvict(value = "feed", key = "'allFeed'"),
                    @CacheEvict(value = "page"),
                    @CacheEvict(value = "user_feed", key = "#userId")
            }
    )
    @Override
    public boolean deleteFeedById(String feedId, String userId) {
        return feedDao.deleteFeedById(feedId);
    }

    @Cacheable(value = "user_feed", key = "#userId", condition = "#userId != 'null'")
    @Override
    public List<FeedVo> getFeedByUserId(String userId) {
        List<Feed> allFeed = feedDao.getFeedByUserId(userId);
        List<FeedVo> feedVos = new LinkedList<>();
        for (Feed feed : allFeed) {
            FeedVo feedVo = new FeedVo();
            User user = userDao.getById(feed.getUserId());
            List<String> likesByFeedId = likeService.getLikesByFeedId(feed.getFeedId());
            List<Comment> commentsByFeedId = commentService.getCommentByFeedId(feed.getFeedId());
            feedVo.setFeedId(feed.getFeedId());
            feedVo.setUserId(feed.getUserId());
            feedVo.setContent(feed.getContent());
            feedVo.setImageUrl(feed.getImageUrl());
            feedVo.setCreateTime(feed.getCreateTime());
            feedVo.setUpdateTime(feed.getUpdateTime());
            feedVo.setUserAvatar(user.getAvatar());
            feedVo.setUserNickName(user.getNickName());
            feedVo.setUserEmail(user.getEmail());
            feedVo.setLikedUserIds(likesByFeedId);
            feedVo.setComments(commentsByFeedId);
            feedVos.add(feedVo);
        }
        return feedVos;
    }

    @Caching(
            put = @CachePut(value = "feed", key = "#feed.feedId"),
            evict = {
                    @CacheEvict(value = "feed", key = "'allFeed'"),
                    @CacheEvict(value = "page"),
                    @CacheEvict(value = "user_feed", key = "#feed.userId")
            }

    )
    @Override
    public boolean updateFeed(Feed feed) {
        String feedId = feed.getFeedId();
        String nowTime = TimeUtils.getNowTime();
        feed.setUpdateTime(nowTime);
        return feedDao.updateFeed(feedId, feed);
    }

    // 分页查询，每页10条数据
    @Cacheable(value = "page", key = "'page_' + #page")
    @Override
    public List<FeedVo> getFeedByPage(int page, int size) {
        List<Feed> feedByPage = feedDao.getFeedByPage(page, size);
        if (feedByPage == null) {
            return new ArrayList<FeedVo>();
        }
        List<FeedVo> feedVos = new LinkedList<>();
        for (Feed feed : feedByPage) {
            FeedVo feedVo = new FeedVo();
            User user = userDao.getById(feed.getUserId());
            List<String> likesByFeedId = likeService.getLikesByFeedId(feed.getFeedId());
            List<Comment> commentsByFeedId = commentService.getCommentByFeedId(feed.getFeedId());
            feedVo.setFeedId(feed.getFeedId());
            feedVo.setUserId(feed.getUserId());
            feedVo.setContent(feed.getContent());
            feedVo.setImageUrl(feed.getImageUrl());
            feedVo.setCreateTime(feed.getCreateTime());
            feedVo.setUpdateTime(feed.getUpdateTime());
            feedVo.setUserAvatar(user.getAvatar());
            feedVo.setUserNickName(user.getNickName());
            feedVo.setUserEmail(user.getEmail());
            feedVo.setLikedUserIds(likesByFeedId);
            feedVo.setComments(commentsByFeedId);
            feedVos.add(feedVo);
        }
        return feedVos;
    }

    @Override
    public List<FeedVo> getFollowedFeedByPage(String userId, int page, int size) {
        List<String> followedUserIds = followDao.getFollowedUserIds(userId);

        List<Feed> feedByPage = feedDao.getFollowedFeedByPage(followedUserIds, page, size);
        if (feedByPage == null) {
            return new ArrayList<FeedVo>();
        }
        List<FeedVo> feedVos = new LinkedList<>();
        for (Feed feed : feedByPage) {
            FeedVo feedVo = new FeedVo();
            User user = userDao.getById(feed.getUserId());
            List<String> likesByFeedId = likeService.getLikesByFeedId(feed.getFeedId());
            List<Comment> commentsByFeedId = commentService.getCommentByFeedId(feed.getFeedId());
            feedVo.setFeedId(feed.getFeedId());
            feedVo.setUserId(feed.getUserId());
            feedVo.setContent(feed.getContent());
            feedVo.setImageUrl(feed.getImageUrl());
            feedVo.setCreateTime(feed.getCreateTime());
            feedVo.setUpdateTime(feed.getUpdateTime());
            feedVo.setUserAvatar(user.getAvatar());
            feedVo.setUserNickName(user.getNickName());
            feedVo.setUserEmail(user.getEmail());
            feedVo.setLikedUserIds(likesByFeedId);
            feedVo.setComments(commentsByFeedId);
            feedVos.add(feedVo);
        }
        return feedVos;
    }

    @Override
    public List<FeedVo> findByContentLike(String content) {
        List<Feed> byContentLike = feedDao.findByContentLike(content);
        if (byContentLike != null) {
            List<FeedVo> feedVos = new LinkedList<>();
            for (Feed feed : byContentLike) {
                FeedVo feedVo = new FeedVo();
                User user = userDao.getById(feed.getUserId());
                List<String> likesByFeedId = likeService.getLikesByFeedId(feed.getFeedId());
                List<Comment> commentsByFeedId = commentService.getCommentByFeedId(feed.getFeedId());
                feedVo.setFeedId(feed.getFeedId());
                feedVo.setUserId(feed.getUserId());
                feedVo.setContent(feed.getContent());
                feedVo.setImageUrl(feed.getImageUrl());
                feedVo.setCreateTime(feed.getCreateTime());
                feedVo.setUpdateTime(feed.getUpdateTime());
                feedVo.setUserAvatar(user.getAvatar());
                feedVo.setUserNickName(user.getNickName());
                feedVo.setUserEmail(user.getEmail());
                feedVo.setLikedUserIds(likesByFeedId);
                feedVo.setComments(commentsByFeedId);
                feedVos.add(feedVo);
            }
            return feedVos;
        }

        return new ArrayList<FeedVo>();
    }
}
