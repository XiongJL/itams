package com.liwinon.itams.controller;

import com.liwinon.itams.dao.primaryRepo.*;
import com.liwinon.itams.entity.model.RoleModel;
import com.liwinon.itams.service.showService;
import com.liwinon.itams.service.userService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class permissionController {
    @Autowired
    UserInfoDao infodao;
    @Autowired
    showService showService;
    @Autowired
    AssetsDao asDao;
    @Autowired
    UserDao userDao;
    @Autowired
    RoleDao roleDao;
    @Autowired
    UserRoleDao urDao;
    @Autowired
    ApplyDao applyDao;
    @Autowired
    userService userService;
    @GetMapping(value = "/itams/role")
    public String role(){
        return "common/role";
    }
    //新版权限管理
    @GetMapping(value = "/itams/newrole")
    public String newrole(){
        return "role/role";
    }

    @GetMapping(value = "/itams/role/getData")
    @ResponseBody
    public JSONObject getrole(int limit, int offset, String content,String type){
        return userService.getrole(limit,offset,content,type);
    }


    @GetMapping(value = "/itams/role/getMyRole")
    @ResponseBody
    public String getMyrole(HttpServletRequest request){
        return userService.getMyrole(request);
    }



    /**
     * 获取所有的workshop
     * @return
     */
    @GetMapping(value = "/itams/role/getWorkshop")
    @ResponseBody
    public List<String> getWorkShops(){
        return userService.getWorkShops();
    }
    /**
     * 根据 工号或用户名搜索权限
     */
    @GetMapping(value = "/itams/role/getRoleByIdAndUser")
    @ResponseBody
    public RoleModel getRoleByIdAndUser(String content){
        return userService.getRoleByIdAndUser(content);
    }


    /**
     * 更新用户权限
     * @param request
     * @return
     */
    @GetMapping(value = "/itams/role/update")
    @ResponseBody
    public String updateRole(HttpServletRequest request){
        return userService.updateRole(request);
    }


    @GetMapping(value = "/itams/role/del")
    @ResponseBody
    public String  delUser(String id){
       if (StringUtils.isEmpty(id)){
           return "empty";
       }
        return userService.delUser(Integer.valueOf(id));
    }


    /**
     * 申请临时权限  ---是在现有基础上增加权限
     * @Param
     * @return
     */
    @GetMapping(value = "/itams/role/apply")
    @ResponseBody
    public String applyRole(String role,String reason,HttpServletRequest request){
        return userService.applyRole(role,reason,request);
    }

    @GetMapping(value = "/itams/role/doIApply")
    @ResponseBody
    public String doIApply(HttpServletRequest request){
        return userService.doIApply(request);
    }

    @GetMapping(value = "/itams/role/whatIApply")
    @ResponseBody
    public String whatIApply(HttpServletRequest request){
        return userService.whatIApply(request);
    }
}
