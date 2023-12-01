package cn.simida.socialFeed.controller;

import cn.simida.common.pojo.User;
import cn.simida.socialFeed.service.LikeService;
import cn.simida.utils.ErrorCode;
import cn.simida.utils.JWTUtils;
import cn.simida.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Cikian
 * @version 1.0
 * @description TODO
 * @date 2023/11/14 19:24
 */
@RestController
@RequestMapping("/like")
public class LikeController {
    @Autowired
    LikeService likeService;

    @PostMapping
    public Result addLike(HttpServletRequest request, @RequestParam String feedId, @RequestParam String feedUserId) throws Exception {
        String token = request.getHeader("Authorization");
        User user = JWTUtils.getUserByToken(token);
        boolean flag = likeService.addLike(feedId, user.getUserId(), feedUserId);
        String msg = flag ? "点赞成功" : "系统异常，请稍后再试";
        Integer code = flag ? ErrorCode.ADD_SUCCESS : ErrorCode.ADD_FAIL;
        return new Result(code, null, msg);
    }

    @DeleteMapping
    public Result deleteLike(HttpServletRequest request, @RequestParam String feedId, @RequestParam String feedUserId) throws Exception {
        String token = request.getHeader("Authorization");
        User user = JWTUtils.getUserByToken(token);
        boolean flag = likeService.deleteLike(feedId, user.getUserId(),feedUserId);
        String msg = flag ? "取消点赞成功" : "系统异常，请稍后再试";
        Integer code = flag ? ErrorCode.DELETE_SUCCESS : ErrorCode.DELETE_FAIL;
        return new Result(code, null, msg);
    }

    @GetMapping("/feed/{feedId}")
    public Result getLikesByFeedId(@PathVariable String feedId) {
        List<String> likedUserIds = likeService.getLikesByFeedId(feedId);
        String msg = likedUserIds == null ? "查询失败" : "查询成功";
        Integer code = likedUserIds == null ? ErrorCode.GET_FAIL : ErrorCode.GET_SUCCESS;
        return new Result(code, likedUserIds, msg);
    }

    @GetMapping("/user/{userId}")
    public Result getLikesByUserId(@PathVariable String userId) {
        List<String> userLikedFeeds = likeService.getLikesByUserId(userId);
        String msg = userLikedFeeds == null ? "查询失败" : "查询成功";
        Integer code = userLikedFeeds == null ? ErrorCode.GET_FAIL : ErrorCode.GET_SUCCESS;
        return new Result(code, userLikedFeeds, msg);
    }
}
