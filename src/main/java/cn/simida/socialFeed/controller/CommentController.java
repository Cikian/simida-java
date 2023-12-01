package cn.simida.socialFeed.controller;

import cn.simida.socialFeed.pojo.Comment;
import cn.simida.socialFeed.service.CommentService;
import cn.simida.utils.ErrorCode;
import cn.simida.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Cikian
 * @version 1.0
 * @description TODO
 * @date 2023/11/20 17:12
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    // 添加评论
    @PostMapping
    public Result addComment(@RequestBody Comment comment) {
        boolean flag = commentService.addComment(comment);
        String msg = flag ? "评论成功" : "评论失败";
        Integer code = flag ? ErrorCode.ADD_SUCCESS : ErrorCode.ADD_FAIL;
        return new Result(code, null, msg);
    }

}
