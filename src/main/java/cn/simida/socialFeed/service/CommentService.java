package cn.simida.socialFeed.service;

import cn.simida.socialFeed.pojo.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getCommentByFeedId(String feedId);

    boolean addComment(Comment comment);
}
