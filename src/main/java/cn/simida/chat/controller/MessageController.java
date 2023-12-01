package cn.simida.chat.controller;

import cn.simida.chat.pojo.entity.Message;
import cn.simida.common.pojo.User;
import cn.simida.chat.service.MessageService;
import cn.simida.utils.ErrorCode;
import cn.simida.utils.JWTUtils;
import cn.simida.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/getMesList/{to}/{pageNum}/{pageSize}")
    public Result list(HttpServletRequest request, @PathVariable String to) throws Exception {
        System.out.println("执行了list方法");
        System.out.println("to = " + to);

        String token = request.getHeader("Authorization");
        User user = JWTUtils.getUserByToken(token);
        Map<String, Object> list = messageService.list(user, to);
        Integer code = list != null ? ErrorCode.GET_SUCCESS : ErrorCode.GET_FAIL;
        String msg = list != null ? "获取成功" : "获取失败";
        return new Result(code, list, msg);
    }

    @PostMapping("/sendMes")
    public Result sendMes(HttpServletRequest request, @RequestBody Message message) throws Exception {
        String token = request.getHeader("Authorization");
        User user = JWTUtils.getUserByToken(token);
        message.setFromId(user.getUserId());
        boolean add = messageService.add(message);
        Integer code = add ? ErrorCode.ADD_SUCCESS : ErrorCode.ADD_FAIL;
        String msg = add ? "发送成功" : "发送失败";
        return new Result(code, null, msg);
    }

}
