package com.liwinon.itams.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping(value = "/itams/user")
    public String user(){
        return "user/userMain";
    }
}
