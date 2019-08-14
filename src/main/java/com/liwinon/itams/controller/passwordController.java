package com.liwinon.itams.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liwinon.itams.dao.primaryRepo.UserDao;
import com.liwinon.itams.entity.model.UserRoleModel;
import com.liwinon.itams.entity.primay.User;

@Controller
public class passwordController {
	
	@GetMapping(value = "/itams/adminpassword")
	public String adminpassword() {
		return "adminpassword";
	}
	 @Autowired
	 UserDao userDao;
	
	 /**
     * 修改密码
     * @return
     */
    @PostMapping(value = "/itams/updatepassword")
    @ResponseBody
    public String login(String password1, String password2, HttpServletRequest request){
            HttpSession session = request.getSession();
            User urms = userDao.findByPERSONID(session.getAttribute("userid").toString());
            String p=urms.getPwd();
            if(!password1.equals(p)) {
            	return "no";
            }
            urms.setPwd(password2);
            userDao.save(urms);
            return "ok";
    }
}
