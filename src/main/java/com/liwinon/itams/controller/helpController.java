package com.liwinon.itams.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/itams")
public class helpController {
    @GetMapping(value = "/help")
    public  String help(){
        return "/common/help";
    }
}
