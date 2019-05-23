package com.liwinon.itams.controller;

import com.liwinon.itams.dao.*;
import com.liwinon.itams.entity.*;
import com.liwinon.itams.service.showService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class permissionController {
    @Autowired
    HardwareDao hdDao;
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
    @GetMapping(value = "/itams/role")
    public String role(){
        return "common/role";
    }

    @GetMapping(value = "/itams/role/getData")
    @ResponseBody
    public JSONObject getrole(int limit, int offset, String content,String type){
        System.out.println("页面大小,limit:" + limit);
        System.out.println("页码,offset:" + offset);
        System.out.println("搜索内容,search:" + content);
        System.out.println("搜索类别,type:"+type);
        //不能以有空的字段来排序
        Sort sort = new Sort(Sort.Direction.ASC, "uid");
        //页码从0开始
        Pageable pageable = PageRequest.of(offset-1, limit, sort);
        JSONObject json = new JSONObject();
        if(type==null||type==""){   //没有查询类型,也就是第一次进入,或者刷新,查询全部数据
            Map<String ,Object> res  = tablesRole(pageable);// 部分数据模型
            json = JSONObject.fromObject(res);
            // System.out.println("json:" + json);
            //      test.accumulate("total", 1);
            //    test.accumulate("rows", "[{AssetsID:\"1\",Model:\"ssss\",UserName:\"ssssss\",UserID:\"123\",getTime:\"1920-001\"}]");
            //       System.out.println("test:" + test);
            System.out.println(json);
            return json;
        }
        if (content != null && content != "") {
            //
            System.out.println("开始搜索资产");
            List<DatasShowModel> datas = showService.searchData(content,type);
            json.accumulate("total", datas.size());
            json.accumulate("rows", datas);
            return json;
        }

        return null;
    }
    /**
     * 获取分页的用户角色  方法,记得封装到service
     * @param pageable
     * @return
     */
    public  Map<String ,Object>  tablesRole(Pageable pageable){
        Map<String ,Object> result = new HashMap<>();
        Page<String[]> page = roleDao.getAllUserRole(pageable);
        System.out.println("总数total:"+page.getTotalElements());
        result.put("total",page.getTotalElements());
        List<String []> list = page.getContent();
        List<RoleModel> datas = new ArrayList<>();
        RoleModel data;
        for(Object[] strs : list){
            data = new RoleModel();
            data.setUid((int)(strs[0]));
            data.setUname((String) strs[1]);
            data.setRoles((String)strs[2]);
            datas.add(data);
        }
        System.out.println(list.size());
        result.put("rows",datas);
        return result;
    }

    /**
     * 获取所有的workshop
     * @return
     */
    @GetMapping(value = "/itams/role/getWorkshop")
    @ResponseBody
    public List<String> getWorkShops(){
        List<String> list =  roleDao.findWorkshops();
        for(String s : list ){
            System.out.println(s);
        }
        return list;
    }

    @GetMapping(value = "/itams/role/update")
    @ResponseBody
    public String updateRole(HttpServletRequest request){
        //获取数组用  getParameterValues
        String[] roles=request.getParameterValues("roles[]");
        int uid = Integer.valueOf(request.getParameter("uid"));
        System.out.println(uid);
        //查找关联表
        List<UserRole> list = urDao.findByUid(uid);
        //全部删除
        if (list.size()>0){
            for(UserRole ur:list){
                urDao.deleteById(ur.getId());
            }
        }
        //全部添加
        if(roles.length>0){
            for (String s: roles){ //循环更新的所有权限
                Role  role = roleDao.findByWorkshop(s);
                UserRole a = new UserRole();
                a.setUid(uid);
                a.setRid(role.getRid());
                urDao.save(a);
            }
        }
        return "ok";
    }
}
