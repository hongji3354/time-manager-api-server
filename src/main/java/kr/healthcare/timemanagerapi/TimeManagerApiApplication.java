package kr.healthcare.timemanagerapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // JPA Auditing 활성화
public class TimeManagerApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimeManagerApiApplication.class, args);
    }

}
