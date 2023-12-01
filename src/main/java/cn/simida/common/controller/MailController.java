package cn.simida.common.controller;

import cn.simida.common.pojo.TextMail;
import cn.simida.common.service.MailService;
import cn.simida.utils.ErrorCode;
import cn.simida.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Cikian
 * @version 1.0
 * @description TODO
 * @date 2023/10/29 22:34
 */
@RestController
@RequestMapping("/mail")
public class MailController {
    @Autowired
    private MailService mailService;


    @PostMapping("/sendVerificationCodeHtmlMail")
    public Result sendTemplateMail(@RequestBody TextMail textMail) {
        return mailService.sendVerificationCodeHtmlMail(textMail.getTo(), textMail.getSubject(), textMail.getTitle(), textMail.getMsg());
    }

    @PostMapping("/sendCommentHtmlMail")
    public Result sendCommentHtmlMail(@RequestParam("to") String to,
                                      @RequestParam("subject") String subject,
                                      @RequestParam("title") String title,
                                      @RequestParam("nickName") String nickName,
                                      @RequestParam("fromName") String fromName) {
        System.out.println("to: " + to + " subject: " + subject + " title: " + title + " nickName: " + nickName + " fromName: " + fromName);
        return mailService.sendCommentHtmlMail(to, subject, title, nickName, fromName);
    }

    @PostMapping("/checkVerificationCode")
    public Result checkVerificationCode(@RequestBody Map<String, String> requestPayload) {
        String email = requestPayload.get("email");
        String verificationCode = requestPayload.get("verificationCode");
        System.out.println("email: " + email + " verificationCode: " + verificationCode);
        boolean checkFlag = mailService.checkVerificationCode(email, verificationCode);
        String msg = checkFlag ? "验证码正确" : "验证码错误";
        Integer code = checkFlag ? ErrorCode.COMMON_SUCCESS : ErrorCode.COMMON_FAIL;
        return new Result(code, null, msg);
    }

}
