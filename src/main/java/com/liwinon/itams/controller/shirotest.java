package com.liwinon.itams.controller;

import org.apache.shiro.authz.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/itams")
public class shirotest {
    // 由于ShiroConfig中配置了该路径可以匿名访问，所以这接口不需要登录就能访问
    @GetMapping("/hello")
    public String hello() {
        return "hello spring boot";
    }

    // 如果ShiroConfig中没有配置该路径可以匿名访问，所以直接被登录过滤了。
    // 如果配置了可以匿名访问，那这里在没有登录的时候可以访问，但是用户登录后就不能访问
    @RequiresGuest
    @GetMapping("/guest")
    public String guest() {
        return "@RequiresGuest";
    }

    @RequiresAuthentication
    @GetMapping("/authn")
    public String authn() {
        return "@RequiresAuthentication";
    }

    @RequiresUser
    @GetMapping("/user")
    public String user() {
        return "@RequiresUser";
    }

    @RequiresPermissions("mvn:install")
    @GetMapping("/mvnInstall")
    public String mvnInstall() {
        return "mvn:install";
    }

    @RequiresPermissions("gradleBuild")
    @GetMapping("/gradleBuild")
    public String gradleBuild() {
        return "gradleBuild";
    }


    @RequiresRoles("ROLE_cathode")
    @GetMapping("/cathode")
    public String js() {
        return "js programmer";
    }


    @RequiresRoles("python")
    @GetMapping("/python")
    public String python() {
        return "python programmer";
    }
}
