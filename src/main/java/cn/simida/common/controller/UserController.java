package cn.simida.common.controller;


import cn.simida.common.pojo.User;
import cn.simida.common.pojo.vo.RegisterVo;
import cn.simida.common.service.MailService;
import cn.simida.common.service.UserService;
import cn.simida.socialFeed.pojo.vo.FollowerVo;
import cn.simida.socialFeed.pojo.vo.FollowingVo;
import cn.simida.socialFeed.service.FollowService;
import cn.simida.utils.ErrorCode;
import cn.simida.utils.JWTUtils;
import cn.simida.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Api(tags = "用户接口")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private MailService mailService;
    @Autowired
    private FollowService followService;

    @GetMapping
    @ApiOperation(value = "获取用户信息", notes = "通过token获取用户信息")
    public Result getUser(HttpServletRequest request) throws Exception {
        String token = request.getHeader("Authorization");
        User tokenUser = JWTUtils.getUserByToken(token);
        User user = userService.getById(tokenUser.getUserId());


        List<FollowerVo> followers = followService.getFollowers(tokenUser.getUserId());
        List<FollowingVo> followings = followService.getFollowings(tokenUser.getUserId());

        Map<String, Object> resMap = new HashMap<>();
        resMap.put("userInfo", user);
        resMap.put("followers", followers);
        resMap.put("followings", followings);
        if (user.getNewUser() == 1) {
            userService.changeUserStatus(user.getUserId());
        }
        return new Result(ErrorCode.GET_SUCCESS, resMap, "成功");
    }

    @GetMapping("/{userId}")
    public Result getUserById(@PathVariable String userId) {
        User user = userService.getById(userId);

        List<FollowerVo> followers = followService.getFollowers(userId);
        List<FollowingVo> followings = followService.getFollowings(userId);

        Map<String, Object> resMap = new HashMap<>();
        resMap.put("userInfo", user);
        resMap.put("followers", followers);
        resMap.put("followings", followings);

        Integer code = user != null ? ErrorCode.GET_SUCCESS : ErrorCode.GET_FAIL;
        String msg = user != null ? "查找成功" : "查找失败";
        return new Result(code, resMap, msg);
    }


    @GetMapping("/getUserByUserName/{username}")
    @ApiOperation(value = "查找用户", notes = "通过用户名获取用户信息")
    public Result getUserByUserName(@PathVariable String username) {
        System.out.println("进入getUserByUserName方法:" + username);
        User user = userService.getUserByUserName(username);
        Integer code = user != null ? ErrorCode.GET_SUCCESS : ErrorCode.GET_FAIL;
        String msg = user != null ? "查找成功" : "查找失败";
        return new Result(code, user, msg);
    }

    @PostMapping("/register")
    @ApiOperation(value = "新增用户", notes = "新增用户")
    public Result register(@RequestBody RegisterVo registerVo) {
        User user = registerVo.getUser();
        if (userService.getUserByUserName(user.getUserName()) != null) {
            return new Result(ErrorCode.ADD_FAIL, null, "用户名已存在");
        }
        String verificationCode = registerVo.getVerificationCode();
        boolean checkFlag = mailService.checkVerificationCode(user.getEmail(), verificationCode);
        if (!checkFlag) {
            return new Result(ErrorCode.ADD_FAIL, null, "验证码错误");
        }

        User userSaved = userService.addUser(user);
        if (userSaved != null) {
            followService.addFollowing("1", user.getUserId());
        }
        String msg = userSaved != null ? "注册成功" : "注册失败";
        String url = userSaved != null ? "/user/login" : "/user/register";
        Integer code = userSaved != null ? ErrorCode.ADD_SUCCESS : ErrorCode.ADD_FAIL;
        return new Result(code, url, msg);
    }

    @GetMapping("/getAllUser")
    @ApiOperation(value = "获取所有用户", notes = "获取所有用户")
    public Result getAllUser() {
        System.out.println("进入getAllUser方法");
        List<User> allUsers = userService.getAllUser();
        String msg = allUsers != null ? "查找成功" : "查找失败";
        Integer code = allUsers != null ? ErrorCode.GET_SUCCESS : ErrorCode.GET_FAIL;
        return new Result(code, allUsers, msg);
    }

    @PostMapping("/changeBg")
    public Result changeBg(@RequestBody String url, HttpServletRequest request) throws Exception {
        url = URLDecoder.decode(url, StandardCharsets.UTF_8.toString());
        url = url.replace("=", "");
        System.out.println("tureUrl---" + url);

        String token = request.getHeader("Authorization");
        User user = JWTUtils.getUserByToken(token);
        String newToken = userService.changeBg(url, user);
        String msg = newToken != null ? "修改成功" : "修改失败";
        Integer code = newToken != null ? ErrorCode.UPDATE_SUCCESS : ErrorCode.UPDATE_FAIL;
        return new Result(code, newToken, msg);
    }

    // 获取推荐用户
    @GetMapping("/getRecommendUser")
    public Result getRecommendUser(HttpServletRequest request) throws Exception {
        String token = request.getHeader("Authorization");
        User user = JWTUtils.getUserByToken(token);
        List<User> recommendUser = userService.getRecommendUser(user.getUserId());
        String msg = recommendUser != null ? "查找成功" : "查找失败";
        Integer code = recommendUser != null ? ErrorCode.GET_SUCCESS : ErrorCode.GET_FAIL;
        return new Result(code, recommendUser, msg);
    }

    @PutMapping
    public Result updateUser(HttpServletRequest request, @RequestParam String nickName,
                             @RequestParam String description,
                             @RequestParam String avatar) throws Exception {
        String token = request.getHeader("Authorization");
        User user = JWTUtils.getUserByToken(token);
        user.setNickName(nickName);
        user.setDescription(description);
        user.setAvatar(avatar);
        String newToken = userService.update(user);
        boolean flag = newToken != null;
        Integer code = flag ? ErrorCode.UPDATE_SUCCESS : ErrorCode.UPDATE_FAIL;
        String msg = flag ? "修改成功" : "系统异常，请稍后再试！";
        User updatedUser = flag ? user : null;

        return new Result(code, updatedUser, msg, newToken);
    }

    @PutMapping("/changePassword")
    public Result changePassword(HttpServletRequest request, @RequestParam String verificationCode,
                                 @RequestParam String password) throws Exception {
        String token = request.getHeader("Authorization");
        User user = JWTUtils.getUserByToken(token);
        boolean checkFlag = mailService.checkVerificationCode(user.getEmail(), verificationCode);
        if (!checkFlag) {
            return new Result(ErrorCode.UPDATE_FAIL, null, "验证码错误");
        }

        boolean flag = userService.updatePasswd(password, user);

        String msg = flag ? "修改成功" : "系统异常，请稍后再试！";
        Integer code = flag ? ErrorCode.UPDATE_SUCCESS : ErrorCode.UPDATE_FAIL;
        return new Result(code, null, msg);
    }

    @GetMapping("/searchUser/{nickNameLike}")
    public Result searchUser(@PathVariable String nickNameLike) {
        System.out.println("进入searchUser方法:" + nickNameLike);
        List<User> users = userService.searchUser(nickNameLike);
        String msg = users != null ? "查找成功" : "查找失败";
        Integer code = users != null ? ErrorCode.GET_SUCCESS : ErrorCode.GET_FAIL;
        return new Result(code, users, msg);
    }
}
