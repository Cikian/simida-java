package cn.simida.socialFeed.controller;

import cn.simida.common.pojo.User;
import cn.simida.socialFeed.pojo.Feed;
import cn.simida.socialFeed.pojo.vo.FeedVo;
import cn.simida.socialFeed.service.FeedService;
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
 * @date 2023/11/2 16:24
 */
@RestController
@RequestMapping("/feed")
public class FeedController {
    @Autowired
    FeedService feedService;

    @PostMapping
    public Result addFeed(HttpServletRequest request, @RequestBody Feed feed) throws Exception {
        String token = request.getHeader("Authorization");
        User user = JWTUtils.getUserByToken(token);
        FeedVo savedFeed = feedService.addFeed(feed, user);
        String msg = savedFeed.getFeedId() != null ? "添加成功" : "添加失败";
        Integer code = savedFeed.getFeedId() != null ? ErrorCode.ADD_SUCCESS : ErrorCode.ADD_FAIL;
        return new Result(code, savedFeed, msg);

    }

    @GetMapping
    public Result getAllFeed() {
        List<FeedVo> allFeed = feedService.getAllFeed();
        String msg = allFeed == null ? "查询失败" : "查询成功";
        Integer code = allFeed == null ? ErrorCode.GET_FAIL : ErrorCode.GET_SUCCESS;
        return new Result(code, allFeed, msg);
    }

    @GetMapping("/getFeedByPage/{page}/{size}")
    public Result getFeedByPage(@PathVariable Integer page, @PathVariable Integer size) {
        List<FeedVo> feedByPage = feedService.getFeedByPage(page, size);
        String msg = feedByPage.isEmpty() ? "查询失败" : "查询成功";
        Integer code = feedByPage.isEmpty() ? ErrorCode.GET_FAIL : ErrorCode.GET_SUCCESS;
        return new Result(code, feedByPage, msg);
    }

    @GetMapping("/getFollowedFeedByPage/{page}/{size}")
    public Result getFollowedFeedByPage(HttpServletRequest request, @PathVariable Integer page, @PathVariable Integer size) throws Exception {
        String token = request.getHeader("Authorization");
        User user = JWTUtils.getUserByToken(token);
        List<FeedVo> feedByPage = feedService.getFollowedFeedByPage(user.getUserId(), page, size);
        String msg = feedByPage.isEmpty() ? "查询失败" : "查询成功";
        Integer code = feedByPage.isEmpty() ? ErrorCode.GET_FAIL : ErrorCode.GET_SUCCESS;
        return new Result(code, feedByPage, msg);
    }

    @GetMapping("/{feedId}")
    public Result getFeedById(@PathVariable String feedId) {
        FeedVo feed = feedService.getFeedById(feedId);
        String msg = feed == null ? "查询失败" : "查询成功";
        Integer code = feed == null ? ErrorCode.GET_FAIL : ErrorCode.GET_SUCCESS;
        return new Result(code, feed, msg);
    }

    @DeleteMapping("/{feedId}")
    public Result deleteFeedById(HttpServletRequest request, @PathVariable String feedId) throws Exception {
        String token = request.getHeader("Authorization");
        User user = JWTUtils.getUserByToken(token);
        boolean b = feedService.deleteFeedById(feedId, user.getUserId());
        String msg = b ? "删除成功" : "删除失败";
        Integer code = b ? ErrorCode.DELETE_SUCCESS : ErrorCode.DELETE_FAIL;
        return new Result(code, null, msg);
    }

    @GetMapping("/getFeedByUserId/{userId}")
    public Result getFeedByUserId(@PathVariable String userId) {
        List<FeedVo> feedByUserId = feedService.getFeedByUserId(userId);
        String msg = feedByUserId == null ? "查询失败" : "查询成功";
        Integer code = feedByUserId == null ? ErrorCode.GET_FAIL : ErrorCode.GET_SUCCESS;
        return new Result(code, feedByUserId, msg);
    }

    @PutMapping
    public Result updateFeed(@RequestBody Feed feed) {
        boolean b = feedService.updateFeed(feed);
        String msg = b ? "更新成功" : "更新失败";
        Integer code = b ? ErrorCode.UPDATE_SUCCESS : ErrorCode.UPDATE_FAIL;
        return new Result(code, null, msg);
    }

    @GetMapping("/findByContentLike/{content}")
    public Result findByContentLike(@PathVariable String content) {
        System.out.println("content = " + content);
        List<FeedVo> feedVos = feedService.findByContentLike(content);
        String msg = feedVos == null ? "查询失败" : "查询成功";
        Integer code = feedVos == null ? ErrorCode.GET_FAIL : ErrorCode.GET_SUCCESS;
        return new Result(code, feedVos, msg);
    }


}
