package cn.simida.socialFeed.controller;

import cn.simida.common.pojo.User;
import cn.simida.socialFeed.service.FollowService;
import cn.simida.utils.ErrorCode;
import cn.simida.utils.JWTUtils;
import cn.simida.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Cikian
 * @version 1.0
 * @description TODO
 * @date 2023/11/14 10:27
 */
@RestController
@RequestMapping("/follow")
public class FollowController {
    @Autowired
    FollowService followService;

    @PostMapping
    public Result addFollowing(HttpServletRequest request, @RequestParam String followingId) throws Exception {
        String token = request.getHeader("Authorization");
        User user = JWTUtils.getUserByToken(token);
        boolean flag = followService.addFollowing(user.getUserId(), followingId);
        String msg = flag ? "关注成功" : "系统异常，请稍后再试";
        Integer code = flag ? ErrorCode.ADD_SUCCESS : ErrorCode.ADD_FAIL;
        return new Result(code,null,msg);
    }

    @DeleteMapping
    public Result unFollowing(HttpServletRequest request, @RequestParam String followingId) throws Exception {
        String token = request.getHeader("Authorization");
        User user = JWTUtils.getUserByToken(token);
        boolean flag = followService.unFollowing(user.getUserId(), followingId);
        String msg = flag ? "取消关注成功" : "系统异常，请稍后再试";
        Integer code = flag ? ErrorCode.DELETE_SUCCESS : ErrorCode.DELETE_FAIL;
        return new Result(code,null,msg);
    }
}
