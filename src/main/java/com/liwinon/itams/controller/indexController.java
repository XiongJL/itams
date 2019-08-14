package com.liwinon.itams.controller;

import com.liwinon.itams.dao.primaryRepo.AssetsDao;
import com.liwinon.itams.dao.primaryRepo.UserDao;
import com.liwinon.itams.dao.primaryRepo.UserInfoDao;
import com.liwinon.itams.entity.model.UserRoleModel;
import com.liwinon.itams.entity.primay.Assets;
import com.liwinon.itams.entity.primay.User;
import com.liwinon.itams.entity.primay.UserInfo;
import com.liwinon.itams.service.userService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
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

@Controller
@CrossOrigin
public class indexController {
    @Autowired
    AssetsDao asDao;
    @Autowired
    UserInfoDao infoDao;
    @Autowired
    UserDao userDao;
    @Autowired
    userService userService;

    @GetMapping(value = "/itams/login")
    public String index(String error,Model model){
        System.out.println("错误代码:"+error);
        if ("0".equals(error)){
            model.addAttribute("err","0");
        }
        return "login";
    }
    @GetMapping(value = "/itams/Ineligible")
    public String Ineligible(){
    	return "Ineligible";
    }
    /**
     * 登录验证
     * @return
     */
    @PostMapping(value = "/itams/login")
    @ResponseBody
    public String login(String userid, String password, HttpServletRequest request){
        UsernamePasswordToken token = new UsernamePasswordToken(userid, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            HttpSession session = request.getSession();
            List<UserRoleModel> urms = userDao.findByUserid(userid);
            /** 超级管理员只能有超级管理员权限!!!
             * **/
            String permission = userDao.getPermissionByRole((String) urms.get(0).getName());
            System.out.println(permission+"------------1----");
            session.setAttribute("userid",userid);
            System.out.println(userid+"-------2--------");
            User user = userDao.findByPERSONID(userid);
            session.setAttribute("username",user.getUname());
            System.out.println(user.getUname()+"-----------3----------");
            System.out.println("permission is  : " + permission);
            session.setAttribute("permission",permission);
        } catch (UnknownAccountException e) {
            System.out.println("账户或密码错误");
            e.printStackTrace();
            return "0";
        } catch (ExcessiveAttemptsException e) {
            System.out.println("登录失败次数过多");
            return "1";
        } catch (Exception e){
            e.printStackTrace();
            return "0";
        }
        return "ok";
    }

    @GetMapping(value = "/itams/register")
    public String register(){
        return "register";
    }

    /**
     * 注册
     * @param userid
     * @param username
     * @param password
     * @return
     */
    @PostMapping(value = "/itams/register")
    @ResponseBody
    public String register(String userid,String username,String password){
        return userService.register(userid,username,password);
    }

    /**
     * 获取用户角色 ,这里没有使用shiro ,业务特殊.
     * @return
     */
    @GetMapping(value = "/itams/user/permission")
    @ResponseBody
    public List<String> getPermission(HttpServletRequest request){
        HttpSession session =  request.getSession();
        List<UserRoleModel> urms = userDao.findByUserid((String) session.getAttribute("userid"));
        List<String> list = new ArrayList<>();
        for (UserRoleModel urm : urms){
            list.add(urm.getWorkshop());
            System.out.println(urm.getWorkshop());
        }

        return list;
    }





    @GetMapping(value = "/itams/operate")
    public String operate(String DeviceID, Model model){
        if(DeviceID!=null && DeviceID!=""){
            //验证IE管理员及其以上身份才能修改
            Subject subject = SecurityUtils.getSubject();
            if(subject.hasRole("ROLE_admin")){
            //有权限
                System.out.println("拥有权限");
                System.out.println("修改"+DeviceID);
                Assets as = asDao.findByDeviceID(DeviceID);
                if(as!=null){
                    UserInfo user = infoDao.findByDeviceID(as.getDeviceID());
                    model.addAttribute("user",user);
                    model.addAttribute("Assets",as);
                }
            }else{
            //没有权限
                System.out.println("无权限");
                return "showData/showData";
            }

        }
        return "operate/operate";
    }
}
