package kr.healthcare.timemanagerapi.config;

import kr.healthcare.timemanagerapi.interceptor.UserTokenValidationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    UserTokenValidationInterceptor userTokenValidationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userTokenValidationInterceptor)
                .addPathPatterns("/api/user/**");
    }
}
