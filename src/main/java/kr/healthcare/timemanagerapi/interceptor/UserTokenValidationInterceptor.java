package kr.healthcare.timemanagerapi.interceptor;

import kr.healthcare.timemanagerapi.domain.member.Member;
import kr.healthcare.timemanagerapi.exception.TokenAuthorizationException;
import kr.healthcare.timemanagerapi.exception.TokenEmptyException;
import kr.healthcare.timemanagerapi.service.member.MemberSerivce;
import kr.healthcare.timemanagerapi.util.JwtTokenUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class UserTokenValidationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    MemberSerivce memberSerivce;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authentication");

        if(token == null || "".equals(token)){
            throw new TokenEmptyException();
        }else{
            Member member = memberSerivce.getMemberFromToken(token);
            Map claims = JwtTokenUtil.getAllClaimsFromToken(token);
            if(claims.get("authorization").equals(member.getAuth())){
                return true;
            }else{
                throw new TokenAuthorizationException();
            }
        }
    }
}
