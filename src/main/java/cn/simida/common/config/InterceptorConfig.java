package cn.simida.common.config;

import cn.simida.common.interceptor.JWTInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    String[] excludePatterns = new String[]{
            "/",
            "/index.html",
            "/css/**",
            "/js/**",
            "/favicon.ico",
            "/login",
            "/user/register",
            "/user/logout",
            "/doc.html",
            "/swagger-resources/**",
            "/webjars/**",
            "/v2/**",
            "/swagger-ui.html/**",
            "/api",
            "/api-docs",
            "/api-docs/**",
            "/doc.html/**",
            "/mail/**",
            "/user/addUser",
            "/user/register",
            "/feed",
            "/upload",
            "/feed/getFeedByPage/**",
            "/feed/findByContentLike/**",
    };
    @Autowired
    private JWTInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(excludePatterns);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
