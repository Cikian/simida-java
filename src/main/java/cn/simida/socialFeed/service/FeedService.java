package cn.simida.socialFeed.service;

import cn.simida.common.pojo.User;
import cn.simida.socialFeed.pojo.Feed;
import cn.simida.socialFeed.pojo.vo.FeedVo;

import java.util.List;

public interface FeedService {
    FeedVo addFeed(Feed feed, User user);
    List<FeedVo> getAllFeed();
    FeedVo getFeedById(String feedId);
    boolean deleteFeedById(String feedId,String userId);
    List<FeedVo> getFeedByUserId(String userId);
    boolean updateFeed(Feed feed);
    List<FeedVo> getFeedByPage(int page, int size);
    List<FeedVo> getFollowedFeedByPage(String userId, int page, int size);
    List<FeedVo> findByContentLike(String content);
}
