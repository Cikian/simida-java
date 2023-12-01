package cn.simida.common.service;

import cn.simida.utils.Result;

public interface MailService {
    Result sendVerificationCodeHtmlMail(String to, String subject, String title, String msg);
    Result sendHtmlMail(String to, String subject, String content);

    Result sendCommentHtmlMail(String to, String subject, String title, String nickname, String fromName);
    boolean checkVerificationCode(String email, String code);
}
