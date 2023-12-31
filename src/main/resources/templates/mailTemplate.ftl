<!DOCTYPE html>
<html lang="zh-Cn" style="margin: 0; padding: 0">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="email code">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>邮件通知</title>
</head>
<!--邮箱验证码模板-->
<body style="margin: 0; padding: 0">
<div style="background-color:#ECECEC; padding: 35px;">
    <table cellpadding="0" align="center"
           style="width: 100%; height: 100%; margin: 0 auto; text-align: left; position: relative; border-top-left-radius: 5px; border-top-right-radius: 5px; border-bottom-right-radius: 5px; border-bottom-left-radius: 5px; font-size: 14px; font-family:微软雅黑, 黑体; line-height: 1.5; box-shadow: rgb(153, 153, 153) 0px 0px 5px; border-collapse: collapse; background-position: initial; background-repeat: initial;background:#fff;">
        <tbody>
        <tr>
            <th valign="middle"
                style="height: 25px; line-height: 25px; padding: 15px 35px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: RGB(148,0,211); background-color: RGB(148,0,211); border-top-left-radius: 5px; border-top-right-radius: 5px; border-bottom-right-radius: 0px; border-bottom-left-radius: 0px;">
                <font face="微软雅黑" size="5" style="color: rgb(255, 255, 255); ">${title}</font>
            </th>
        </tr>
        <tr>
            <td style="word-break:break-all">
                <div style="padding:25px 35px 40px; background-color:#fff;opacity:0.8;">

                    <h2 style="margin: 5px 0px; ">
                        <font color="#333333" style="line-height: 20px; ">
                            <font style="line-height: 22px; " size="4">
                                尊敬的用户：</font>
                        </font>
                    </h2>
                    <!-- 中文 -->
                    <p>您好！感谢您使用Simida，您的账号正在进行${msg}操作，验证码为：<span
                            style="color: #ff8c00; font-size: 25px">${code}</span>，有效期10分钟，请尽快填写验证码完成验证！
                    </p><br>
                    <!-- 英文 -->
                    <h2 style="margin: 5px 0px; ">
                        <font color="#333333" style="line-height: 20px; ">
                            <font style="line-height: 22px; " size="4">
                                Dear user:</font>
                        </font>
                    </h2>
                    <p>Thanks for using Simida, your account is being authenticated by email, the
                        verification code is:<span style="color: #ff8c00; font-size: 18px">${code}</span>, valid for 10 minutes. Please fill in the
                        verification code as soon as
                        possible!</p>
                    <div style="width:100%;margin:0 auto;">
                        <div style="padding:10px 10px 0;border-top:1px solid #ccc;color:#747474;margin-bottom:20px;line-height:1.3em;font-size:12px;">
                            <p>Cikian团队</p>
                            <p>${sendTime}</p>
                            <p>联系我们：${fromName}</p>
                            <br>
                            <p>此为系统邮件，请勿回复<br>
                                Please do not reply to this system email
                            </p>
                            <!--<p>©***</p>-->
                        </div>
                    </div>
                </div>
            </td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>

