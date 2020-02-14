package kr.healthcare.timemanagerapi.interceptor;

import kr.healthcare.timemanagerapi.account.service.UserAccountService;
import kr.healthcare.timemanagerapi.exception.AuthenticationEmptyException;
import kr.healthcare.timemanagerapi.exception.AuthenticationException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserCheckInterceptor implements HandlerInterceptor {

    @NonNull
    private UserAccountService userAccountService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String Authentication = request.getHeader("Authentication");

        if(Authentication == null){
            throw new AuthenticationEmptyException("Token이 비어있습니다.");
        }else if(!userAccountService.tokenCheck(Authentication)){
            throw new AuthenticationException("일치하는 토큰이 없습니다.");
        }


        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
