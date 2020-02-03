package kr.healthcare.timemanagerapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements ApplicationRunner {

    @Value("${driverClassName}")
    String driverClassName;
    @Value("${jdbcUrl}")
    String jdbcUrl;
    @Value("${userName}")
    String userName;
    @Value("${password}")
    String password;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(driverClassName);
        System.out.println(jdbcUrl);
        System.out.println(userName);
        System.out.println(password);
    }
}
