package kr.healthcare.timemanagerapi.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import kr.healthcare.timemanagerapi.account.vo.UserAccountVO;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

public class JWT {

    private static String secretKey = "healthcare";

    private static byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(secretKey);

    private static SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    private static final Key KEY =  new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());

    public static String getToken(UserAccountVO userAccountVO){
        String jwt = Jwts.builder()
                    .setHeaderParam("typ","JWT")
                    .setIssuer("unist_healthcare")
                    .setAudience("user")
                    .claim("uid",userAccountVO.getMemberId())
                    .signWith(signatureAlgorithm,KEY)
                    .compact();
        return jwt;
    }
}
