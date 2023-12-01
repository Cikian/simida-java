package cn.simida.chat.service.impl;

import cn.simida.common.dao.UserDao;
import cn.simida.common.pojo.User;
import cn.simida.chat.service.LoginService;
import cn.simida.utils.ErrorCode;
import cn.simida.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserDao userDao;


    @Override
    public Map<String, String> login(Map<String, String> map) {
        String username = map.get("userName");
        String password = map.get("password");
        String passwordStr = password + "cikian";
        String md5Password = DigestUtils.md5DigestAsHex(passwordStr.getBytes(StandardCharsets.UTF_8));
        Map<String, String> resultMap = new HashMap<>();
        User user = userDao.getUserByUserName(username);
        if (user == null) {
            resultMap.put("code", ErrorCode.GET_FAIL.toString());
            return resultMap;
        }

        if (!user.getPassword().equals(md5Password)) {
            resultMap.put("code", ErrorCode.LOGIN_FAIL.toString());
            return resultMap;
        }
        String token = JWTUtils.createJWT(user, "login", null);
        resultMap.put("code", ErrorCode.LOGIN_SUCCESS.toString());
        resultMap.put("uid", user.getUserId());
        resultMap.put("token", token);
        return resultMap;
    }
}
