package cn.simida.socialFeed.service.impl;

import cn.simida.common.pojo.User;
import cn.simida.common.service.UserService;
import cn.simida.socialFeed.dao.CommentDao;
import cn.simida.socialFeed.pojo.Comment;
import cn.simida.socialFeed.service.CommentService;
import cn.simida.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Cikian
 * @version 1.0
 * @description TODO
 * @date 2023/11/14 22:34
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentDao commentDao;
    @Autowired
    UserService userService;
    @Override
    public List<Comment> getCommentByFeedId(String feedId) {
        List<Comment> commentByFeedId = commentDao.getCommentByFeedId(feedId);

        for (int i = 0; i < commentByFeedId.size(); i++) {
            Comment comment = commentByFeedId.get(i);
            User user = userService.getById(comment.getUserId());
            User toUser = userService.getById(comment.getToUserId());
            if (toUser != null) {
                comment.setToUserNickName(toUser.getNickName());
            }
            comment.setUserNickName(user.getNickName());
            comment.setUserAvatar(user.getAvatar());

            commentByFeedId.set(i, comment);
        }
        return commentByFeedId;
    }

    @Caching(
            evict = {
                    @CacheEvict(value = "feed", key = "#comment.feedId"),
            }
    )
    @Override
    public boolean addComment(Comment comment) {
        String nowTime = TimeUtils.getNowTime();
        comment.setCreateTime(nowTime);
        comment.setUpdateTime(nowTime);
        Comment savedComment = commentDao.addComment(comment);
        return savedComment != null;
    }
}
