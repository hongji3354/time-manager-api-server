package kr.healthcare.timemanagerapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource(name = "tokenValidInterceptor")
    private HandlerInterceptor tokenValidInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> pathPattern = new ArrayList<>();
        pathPattern.add("/api/user/**");
        pathPattern.add("/api/admin/**");
        pathPattern.add("/api/sadmin/**");
        registry.addInterceptor(tokenValidInterceptor)
                .addPathPatterns(pathPattern);

    }
}
