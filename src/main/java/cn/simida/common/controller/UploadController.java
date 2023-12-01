package cn.simida.common.controller;

import cn.simida.common.pojo.User;
import cn.simida.utils.FileUtils;
import cn.simida.utils.JWTUtils;
import cn.simida.utils.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Cikian
 * @version 1.0
 * @description TODO
 * @date 2023/11/3 15:44
 */
@RestController
@RequestMapping("/upload")
public class UploadController {
    @PostMapping
    public Result upload(MultipartFile file, HttpServletRequest request, Integer operationCode) throws Exception {
        System.out.println("file = " + file);
        String token = request.getHeader("Authorization");
        String userId;
        String uploadFilePath = null;
        if (token == null) {
            System.out.println("token为空");
            userId = null;
        } else {
            System.out.println("token不为空");
            System.out.println("token = " + token);
            User user = JWTUtils.getUserByToken(token);
            System.out.println("user = " + user);
            userId = user.getUserId();
            System.out.println("userId = " + userId);
        }
        System.out.println("controller末尾");
        return FileUtils.uploadToUpYun(file, userId, operationCode);
    }
}
