package cn.simida.chat.controller;


import cn.simida.chat.pojo.entity.Apply;
import cn.simida.chat.pojo.entity.Friend;
import cn.simida.common.pojo.User;
import cn.simida.chat.service.FriendService;
import cn.simida.utils.ErrorCode;
import cn.simida.utils.JWTUtils;
import cn.simida.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/friend")
@Api(tags = "好友接口")
public class FriendController {

    @Autowired
    private FriendService friendService;

    //获取好友列表
    @GetMapping("/list/{uid}")
    @ApiOperation(value = "获取好友列表",notes = "获取好友列表")
    public Result list(@PathVariable String uid){
        List<User> friendList = friendService.getFriendsList(uid);
        Integer code = friendList != null ? ErrorCode.GET_SUCCESS : ErrorCode.GET_FAIL;
        String msg = friendList != null ? "查找成功" : "查找失败";
        return new Result(code, friendList, msg);
    }


    //添加好友
    @PostMapping("/addFriend")
    @ApiOperation(value = "添加好友", notes = "添加好友")
    public Result add(@RequestBody Apply apply, HttpServletRequest request) throws Exception {
        System.out.println("apply = " + apply);
        String token = request.getHeader("Authorization");
        User user = JWTUtils.getUserByToken(token);
        apply.setUid(user.getUserId());
        boolean addFlag = friendService.add(apply);
        Integer code = addFlag ? ErrorCode.ADD_SUCCESS : ErrorCode.ADD_FAIL;
        String msg = addFlag ? "发送成功" : "发送失败";
        return new Result(code, null, msg);
    }



    @GetMapping("/getApply")
    @ApiOperation(value = "获取申请列表", notes = "获取申请列表")
    public Result getFriends(HttpServletRequest request) throws Exception {
        String token = request.getHeader("Authorization");
        User user = JWTUtils.getUserByToken(token);
        List<User> apply = friendService.getApply(user.getUserId());
        Integer code = apply != null ? ErrorCode.GET_SUCCESS : ErrorCode.GET_FAIL;
        String msg = apply != null ? "查找成功" : "查找失败";
        return new Result(code, apply, msg);
    }
    //同意
    @PostMapping("/agree")
    public Result agree(HttpServletRequest request, @RequestBody Friend friend) throws Exception {
        String token = request.getHeader("Authorization");
        User user = JWTUtils.getUserByToken(token);
        friend.setUid(user.getUserId());
        boolean agreeFlag = friendService.agree(friend);
        Integer code = agreeFlag ? ErrorCode.ADD_SUCCESS : ErrorCode.ADD_FAIL;
        String msg = agreeFlag ? "添加成功" : "添加失败";
        return new Result(code, null, msg);
    }


}
