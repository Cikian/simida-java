package cn.simida.socialFeed.dao;

import cn.simida.socialFeed.pojo.Comment;

import java.util.List;

public interface CommentDao {
    List<Comment> getCommentByFeedId(String feedId);
    Comment addComment(Comment comment);
}
