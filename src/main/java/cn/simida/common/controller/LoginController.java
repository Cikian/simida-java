package cn.simida.common.controller;


import cn.simida.chat.service.LoginService;
import cn.simida.utils.ErrorCode;
import cn.simida.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/login")
@Api(tags = "登录接口")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    @ApiOperation(value = "登录", notes = "登录")
    public Result login(@RequestBody Map<String, String> map) {
        System.out.println("map = " + map);
        if (CollectionUtils.isEmpty(map)) {
            return new Result(ErrorCode.LOGIN_FAIL, null, "参数不能为空");
        }
        Map<String, String> res = loginService.login(map);
        if (res.get("code").equals(ErrorCode.GET_FAIL.toString())) {
            return new Result(ErrorCode.GET_FAIL, null, "用户不存在");
        } else if (res.get("code").equals(ErrorCode.LOGIN_FAIL.toString())) {
            return new Result(ErrorCode.LOGIN_FAIL, null, "密码错误");
        }
        return new Result(ErrorCode.LOGIN_SUCCESS, res, "登录成功");
    }


}
