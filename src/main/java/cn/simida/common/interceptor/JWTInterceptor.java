package cn.simida.common.interceptor;

import cn.simida.common.exception.BusinessException;
import cn.simida.utils.ErrorCode;
import cn.simida.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author Cikian
 * @version 1.0
 * @description TODO
 * @date 2023/10/19 14:42
 */

@Component
public class JWTInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("------进入拦截器------");

        // 获取请求头中的令牌
        String token = request.getHeader("Authorization");
        if (token == null) {
            throw new BusinessException(ErrorCode.BUSINESS_ERR, "无token,请重新登录，请重新登录");
        } else {
            Claims claims = null;
            try {
                claims = JWTUtils.parseJWT(token);
                Date expirationDate = claims.getExpiration();
                Date now = new Date();
                if (now.after(expirationDate)) {
                    throw new BusinessException(ErrorCode.BUSINESS_ERR, "登录过期，请刷新页面重新登录");
                }
            } catch (SignatureException e) {
                throw new BusinessException(ErrorCode.BUSINESS_ERR, "签名错误，请重新登录");
            } catch (ExpiredJwtException e) {
                throw new BusinessException(ErrorCode.BUSINESS_ERR, "登录过期，请刷新页面重新登录");
            } catch (Exception e) {
                throw new BusinessException(ErrorCode.BUSINESS_ERR, "token无效，请重新登录");
            }
            return true;
        }
    }
}
