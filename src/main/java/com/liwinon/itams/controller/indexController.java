package com.liwinon.itams.controller;

import com.liwinon.itams.dao.AssetsDao;
import com.liwinon.itams.dao.HardwareDao;
import com.liwinon.itams.dao.UserDao;
import com.liwinon.itams.dao.UserInfoDao;
import com.liwinon.itams.entity.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@CrossOrigin
public class indexController {
    @Autowired
    AssetsDao asDao;
    @Autowired
    HardwareDao hdDao;
    @Autowired
    UserInfoDao infoDao;
    @Autowired
    UserDao userDao;

    @GetMapping(value = "/itams/login")
    public String index(String error,Model model){
        System.out.println("错误代码:"+error);
        if ("0".equals(error)){
            model.addAttribute("err","0");
        }
        return "login";
    }

    /**
     * 获取用户角色 ,这里没有使用shiro ,业务特殊.
     * @return
     */
    @GetMapping(value = "/itams/user/permission")
    @ResponseBody
    public List<String> getPermission(HttpServletRequest request){
        HttpSession session =  request.getSession();
        List<UserRoleModel> urms = userDao.findByUname((String) session.getAttribute("username"));
        List<String> list = new ArrayList<>();
        for (UserRoleModel urm : urms){
            list.add(urm.getWorkshop());
            System.out.println(urm.getWorkshop());
        }

        return list;
    }

    /**
     * 登录验证
     * @return
     */
    @PostMapping(value = "/itams/login")
    @ResponseBody
    public String login(String username, String password, HttpServletRequest request){
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            HttpSession session = request.getSession();
            List<UserRoleModel> urms = userDao.findByUname(username);
            /** 超级管理员只能有超级管理员权限!!!
             * **/
            String permission = userDao.getPermissionByRole((String) urms.get(0).getName());
            session.setAttribute("username",username);
            System.out.println("permission is  : " + permission);
            session.setAttribute("permission",permission);
        } catch (UnknownAccountException e) {
            System.out.println("账户或密码错误");
           // e.printStackTrace();
           return "0";
        } catch (ExcessiveAttemptsException e) {
            System.out.println("登录失败次数过多");
            return "1";
        } catch (Exception e){
            e.printStackTrace();
        }
        return "ok";
    }



    @GetMapping(value = "/itams/operate")
    public String operate(String AssetsID, Model model){
        if(AssetsID!=null && AssetsID!=""){
            System.out.println("修改"+AssetsID);
            Assets as = asDao.findByAssetsID(AssetsID);
            if(as!=null){
                if("IT".equals(as.getAssetsCategory())){
                    Hardware hd =  hdDao.findByAssetsID(as.getAssetsID());
                    if(hd!=null){
                        model.addAttribute("HD",hd);
                    }
                }
                UserInfo user = infoDao.findByAssetsID(AssetsID);
                if(user!=null){
                    model.addAttribute("user",user);
                }
                model.addAttribute("Assets",as);
            }
        }


        return "operate/operate";
    }
}
