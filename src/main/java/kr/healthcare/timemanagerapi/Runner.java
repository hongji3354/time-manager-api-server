package kr.healthcare.timemanagerapi;

import io.jsonwebtoken.Jwts;
import kr.healthcare.timemanagerapi.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Runner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}
