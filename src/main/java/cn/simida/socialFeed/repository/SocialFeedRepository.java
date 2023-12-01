package cn.simida.socialFeed.repository;

import cn.simida.socialFeed.pojo.Feed;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface SocialFeedRepository extends MongoRepository<Feed, String> {
    List<Feed> findByUserId(String userId, Sort createTime);

    List<Feed> findByUserIdIn(List<String> followedUserIds);
    List<Feed> findByUserIdIn(List<String> userIds, Sort createTime);

    List<Feed> findByContentRegexIgnoreCaseOrderByCreateTimeDesc(String content);

}
