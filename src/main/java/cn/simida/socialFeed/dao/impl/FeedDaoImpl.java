package cn.simida.socialFeed.dao.impl;

import cn.simida.socialFeed.dao.FeedDao;
import cn.simida.socialFeed.pojo.Feed;
import cn.simida.socialFeed.repository.SocialFeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Cikian
 * @version 1.0
 * @description TODO
 * @date 2023/11/2 16:31
 */
@Repository
public class FeedDaoImpl implements FeedDao {
    @Autowired
    private SocialFeedRepository socialFeedRepository;


    @Override
    public Feed addFeed(Feed feed) {
        System.out.println("feed = " + feed);
        return socialFeedRepository.save(feed);
    }

    // 查询所有
    @Override
    public List<Feed> getAllFeed() {

        return socialFeedRepository.findAll(Sort.by(Sort.Direction.DESC, "createTime"));
    }

    @Override
    public Feed getFeedById(String feedId) {
        return socialFeedRepository.findById(feedId).orElse(null);
    }

    // 删除
    @Override
    public boolean deleteFeedById(String feedId) {
        try {
            socialFeedRepository.deleteById(feedId);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public List<Feed> getFeedByUserId(String userId) {
        // 按照时间降序排列
        return socialFeedRepository.findByUserId(userId, Sort.by(Sort.Direction.DESC, "createTime"));

    }

    @Override
    public boolean updateFeed(String feedId, Feed feed) {
        Optional<Feed> byId = socialFeedRepository.findById(feedId);
        if (byId.isPresent()) {
            Feed feedUpDate = byId.get();
            feedUpDate.setContent(feed.getContent());
            feedUpDate.setUpdateTime(feed.getUpdateTime());
            socialFeedRepository.save(feedUpDate);
            return true;
        }
        return false;
    }

    @Override
    public List<Feed> getFeedByPage(int page, int size) {
        // 总条数
        int count = (int) socialFeedRepository.count();

        int start = (page - 1) * size;

        // 如果起始索引大于最大索引，说明没有数据了
        if (start >= count) {
            return null;
        }

        if (start + size > count) {
            size = count - start;
        }
        return socialFeedRepository.findAll(Sort.by(Sort.Direction.DESC, "createTime")).subList(start, start + size);
    }

    @Override
    public List<Feed> getFollowedFeedByPage(List<String> followedUserIds, int page, int size) {
        // 总条数
        int count = socialFeedRepository.findByUserIdIn(followedUserIds).size();
        System.out.println("count = " + count);
        int start = (page - 1) * size;

        // 如果起始索引大于最大索引，说明没有数据了
        if (start >= count) {
            return null;
        }

        if (start + size > count) {
            size = count - start;
        }
        return socialFeedRepository.findByUserIdIn(followedUserIds, Sort.by(Sort.Direction.DESC, "createTime")).subList(start, start + size);
    }

    // 模糊查询
    @Override
    public List<Feed> findByContentLike(String content) {
        return socialFeedRepository.findByContentRegexIgnoreCaseOrderByCreateTimeDesc(content);
    }


}
