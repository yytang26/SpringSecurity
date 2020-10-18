package cn.tyy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:tyy
 * @version:1.0
 * @Date:2020/10/18 20:40
 */
@RestController
@RequestMapping("/admin/api")
public class AdminController {

    @GetMapping("hello")
    public String hello(){
        return "hello, admin";
    }
}
