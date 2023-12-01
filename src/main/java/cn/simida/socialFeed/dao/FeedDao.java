package cn.simida.socialFeed.dao;

import cn.simida.socialFeed.pojo.Feed;
import cn.simida.socialFeed.pojo.vo.FeedVo;

import java.util.List;

public interface FeedDao {
    Feed addFeed(Feed feed);

    List<Feed> getAllFeed();

    Feed getFeedById(String feedId);

    boolean deleteFeedById(String feedId);

    List<Feed> getFeedByUserId(String userId);

    boolean updateFeed(String feedId, Feed feed);

    List<Feed> getFeedByPage(int page, int size);

    List<Feed> getFollowedFeedByPage(List<String> followedUserIds, int page, int size);

    List<Feed> findByContentLike(String content);
}
