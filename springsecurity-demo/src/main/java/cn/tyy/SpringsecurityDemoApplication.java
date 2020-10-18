package cn.tyy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@MapperScan("cn.tyy.mapper")
public class SpringsecurityDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringsecurityDemoApplication.class, args);
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello,spring security";
    }
}
