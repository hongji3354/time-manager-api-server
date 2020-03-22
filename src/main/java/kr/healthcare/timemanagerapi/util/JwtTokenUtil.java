package kr.healthcare.timemanagerapi.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.Map;

public class JwtTokenUtil {

    private static final String secretKey = "healthcare";

    //JWT 생성
    public static String deGenerateToken(Map<String, Object> claim){
        return Jwts.builder()
                    .setHeaderParam("typ","jwt")
                    .setClaims(claim)
                    .setIssuer("unist_healthcare")
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .signWith(SignatureAlgorithm.HS512,secretKey)
                    .compact();
    }

    //JWT에서 Claim 파싱
    public static Claims getAllClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }


}
