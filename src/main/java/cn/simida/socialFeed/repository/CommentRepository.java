package cn.simida.socialFeed.repository;

import cn.simida.socialFeed.pojo.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> findByFeedId(String feedId, Sort createTime);

}
