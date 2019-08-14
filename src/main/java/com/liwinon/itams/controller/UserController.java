package com.liwinon.itams.controller;

import com.liwinon.itams.dao.secondRepo.SapDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class UserController {
    @Autowired
    SapDao sapDao;

    @GetMapping(value = "/itams/user")
    public String user(){
        return "user/userMain";
    }

    @GetMapping(value = "/itams/user-approval")
    public String approval(){
        return "user/approval";
    }
    

    //获取用户部门
    @GetMapping(value = "/itams/getDep")
    @ResponseBody
    public String getDep(String personid){
        return sapDao.findDepByUserId(personid);
    }
}
