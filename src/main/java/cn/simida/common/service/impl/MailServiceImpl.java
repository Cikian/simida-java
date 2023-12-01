package cn.simida.common.service.impl;

import cn.simida.common.service.MailService;
import cn.simida.utils.ErrorCode;
import cn.simida.utils.Result;
import cn.simida.utils.TimeUtils;
import cn.simida.utils.VerificationCodeUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Cikian
 * @version 1.0
 * @description TODO
 * @date 2023/10/29 22:29
 */
@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSenderImpl javaMailSender;
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;
    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String sendFrom;
    // 注入redisTemplate
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public Result sendVerificationCodeHtmlMail(String to, String subject, String title, String msg) {
        Configuration configuration = new Configuration(Configuration.getVersion());
        configuration.setDefaultEncoding("utf-8");
        Template template;
        try {
            template = freeMarkerConfigurer.getConfiguration().getTemplate("mailTemplate.ftl", "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(ErrorCode.COMMON_FAIL, null, "邮件模板读取失败");
        }

        String nowTime = TimeUtils.getNowTime();
        String VerificationCode = VerificationCodeUtils.generatedCode(6);
        // 将验证码存入redis
        redisTemplate.opsForValue().set("VerificationCode." + to, VerificationCode, 10, TimeUnit.MINUTES);

        Map<String, Object> model = new HashMap<>();
        model.put("fromName", sendFrom);
        model.put("title", title);
        model.put("sendTime", nowTime);
        model.put("code", VerificationCode);
        model.put("msg", msg);

        String templateHtml;
        try {
            templateHtml = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
            return new Result(ErrorCode.COMMON_FAIL, null, "生成邮件模板失败");
        }
        return this.sendHtmlMail(to, subject, templateHtml);
    }

    @Override
    public Result sendCommentHtmlMail(String to, String subject, String title, String nickname, String fromName) {
        Configuration configuration = new Configuration(Configuration.getVersion());
        configuration.setDefaultEncoding("utf-8");
        Template template;
        try {
            template = freeMarkerConfigurer.getConfiguration().getTemplate("commentHtmlTemplate.ftl", "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(ErrorCode.COMMON_FAIL, null, "邮件模板读取失败");
        }

        String nowTime = TimeUtils.getNowTime();
        String VerificationCode = VerificationCodeUtils.generatedCode(6);
        // 将验证码存入redis
        redisTemplate.opsForValue().set("VerificationCode." + to, VerificationCode, 10, TimeUnit.MINUTES);

        Map<String, Object> model = new HashMap<>();
        model.put("title", title);
        model.put("nickName", nickname);
        model.put("fromName", fromName);
        model.put("sendTime", nowTime);


        String templateHtml;
        try {
            templateHtml = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
            return new Result(ErrorCode.COMMON_FAIL, null, "生成邮件模板失败");
        }
        return this.sendHtmlMail(to, subject, templateHtml);
    }

    @Override
    public Result sendHtmlMail(String to, String subject, String content) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("Simida系统邮件 <" + sendFrom + ">");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            return new Result(ErrorCode.COMMON_FAIL, null, "邮件发送失败");
        }
        return new Result(ErrorCode.COMMON_SUCCESS, null, "邮件发送成功");
    }

    @Override
    public boolean checkVerificationCode(String email, String code) {
        String VerificationCode = redisTemplate.opsForValue().get("VerificationCode." + email);
        if (VerificationCode != null && VerificationCode.equals(code)) {
            redisTemplate.delete("VerificationCode." + email);
            return true;
        }
        return false;
    }


}
