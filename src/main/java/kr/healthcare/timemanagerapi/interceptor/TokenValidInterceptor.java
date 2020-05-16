package kr.healthcare.timemanagerapi.interceptor;

import io.jsonwebtoken.MalformedJwtException;
import kr.healthcare.timemanagerapi.constant.Authority;
import kr.healthcare.timemanagerapi.domain.member.MemberEntity;
import kr.healthcare.timemanagerapi.domain.member.MemberRepositroy;
import kr.healthcare.timemanagerapi.exception.TokenEmptyException;
import kr.healthcare.timemanagerapi.exception.UnauthorizedException;
import kr.healthcare.timemanagerapi.util.JwtTokenUtil;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@AllArgsConstructor
public class TokenValidInterceptor extends HandlerInterceptorAdapter {

    @NonNull
    private MemberRepositroy memberRepositroy;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authentication");

        if(token == null || "".equals(token)){
            throw new TokenEmptyException("Token 값이 비어있다.");
        }else{
            // Token이 해당 서버에서 발급했는지 확인. 만약 Token이 올바르지 않다면 MalformedJwtException 발생.
            JwtTokenUtil.getAllClaimsFromToken(token);
        }

        if(memberRepositroy.existsByToken(token)){
            String[] urlSplit = request.getServletPath().split("/");
            MemberEntity memberEntity = memberRepositroy.findByToken(token);
            if("admin".equals(urlSplit[2])){
                if(Authority.U.toString().equals(memberEntity.getAuth())){
                    throw new UnauthorizedException("접근 권한이 없다.");
                }else{
                    return true;
                }
            }else if("sadmin".equals(urlSplit[2])){
                if(Authority.U.toString().equals(memberEntity.getAuth()) || Authority.A.toString().equals(memberEntity.getAuth())){
                    throw new UnauthorizedException("접근 권한이 없다.");
                }else{
                    return true;
                }
            }
        }else{
            throw new MalformedJwtException("Token은 서버에서 발급한게 맞지만 일치하는 Token이 존재하지 않는다!");
        }
    }
}
