package kr.healthcare.timemanagerapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "kr.healthcare.timemanagerapi")
public class TimeManagerApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimeManagerApiApplication.class, args);
    }

}
