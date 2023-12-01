package cn.simida.socialFeed.dao.impl;

import cn.simida.socialFeed.dao.CommentDao;
import cn.simida.socialFeed.pojo.Comment;
import cn.simida.socialFeed.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Cikian
 * @version 1.0
 * @description TODO
 * @date 2023/11/14 22:26
 */
@Repository
public class CommentDaoImpl implements CommentDao {
    @Autowired
    private CommentRepository commentRepository;
    @Override
    public List<Comment> getCommentByFeedId(String feedId) {
        return commentRepository.findByFeedId(feedId, Sort.by(Sort.Direction.DESC, "createTime"));
    }

    @Override
    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }
}
