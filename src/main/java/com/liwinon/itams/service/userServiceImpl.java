package com.liwinon.itams.service;

import com.liwinon.itams.dao.primaryRepo.*;
import com.liwinon.itams.entity.model.DatasShowModel;
import com.liwinon.itams.entity.model.RoleModel;
import com.liwinon.itams.entity.model.UserRoleModel;
import com.liwinon.itams.entity.primay.Apply;
import com.liwinon.itams.entity.primay.Role;
import com.liwinon.itams.entity.primay.User;
import com.liwinon.itams.entity.primay.UserRole;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Service
public class userServiceImpl implements userService{
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
    UserRoleDao userRoleDao;

    public JSONObject getrole(int limit, int offset, String content, String type){
        System.out.println("页面大小,limit:" + limit);
        System.out.println("页码,offset:" + offset);
        System.out.println("搜索内容,search:" + content);
        System.out.println("搜索类别,type:"+type);
        //不能以有空的字段来排序
        Sort sort = new Sort(Sort.Direction.ASC, "uid");
        //页码从0开始
        Pageable pageable = PageRequest.of(offset-1, limit, sort);
        JSONObject json ;
        if(type==null||type==""){   //没有查询类型,也就是第一次进入,或者刷新,查询全部数据
            Map<String ,Object> res  = tablesRole(pageable);// 部分数据模型
            json = JSONObject.fromObject(res);
            System.out.println(json);
            return json;
        }
        if (content != null && content != "") {
            Map<String ,Object> res  = searchtablesRole(content,type,pageable);// 部分数据模型
            json = JSONObject.fromObject(res);
            return json;
        }
        return null;
    }

    /**
     * 返回搜索用户名和工号的Map数据
     * @param content
     * @param type
     * @param pageable
     * @return
     */
    private Map<String, Object> searchtablesRole(String content, String type, Pageable pageable) {
        Map<String ,Object> result = new HashMap<>();
        Page<String[]> page = roleDao.getSearchUserRole(content,pageable);
        System.out.println("总数total:"+page.getTotalElements());
        result.put("total",page.getTotalElements());
        List<String []> list = page.getContent();
        List<RoleModel> datas = new ArrayList<>();
        RoleModel data;
        for(Object[] strs : list){
            data = new RoleModel();
            data.setUid((int)(strs[0]));
            data.setUname((String) strs[1]);
            data.setUserid((String) strs[2]);
            data.setRoles((String)strs[3]);
            datas.add(data);
        }
        System.out.println(list.size());
        result.put("rows",datas);
        return result;
    }

    /**
     * 获取分页的用户角色  方法
     * @param pageable
     * @return
     */
    private Map<String ,Object>  tablesRole(Pageable pageable){
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
            data.setUserid((String) strs[2]);
            data.setRoles((String)strs[3]);
            datas.add(data);
        }
        System.out.println(list.size());
        result.put("rows",datas);
        return result;
    }

    /**
     * 根据 工号或用户名搜索权限
     * @param content
     * @return
     */
    @Override
    public RoleModel getRoleByIdAndUser(String content) {
        Sort sort = new Sort(Sort.Direction.ASC, "uid");
        Pageable pageable = PageRequest.of(0, 10, sort);
        Page<String[]> page = roleDao.getSearchUserRole(content,pageable);
        List<String []> list = page.getContent();
        List<RoleModel> datas = new ArrayList<>();
        RoleModel data;
        for(Object[] strs : list){
            data = new RoleModel();
            data.setUid((int)(strs[0]));
            data.setUname((String) strs[1]);
            data.setUserid((String) strs[2]);
            data.setRoles((String)strs[3]);
            datas.add(data);
        }
        return datas.get(0);
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @Override
    @Transactional
    public String delUser(int id) {
        User user = userDao.findById(id);
        if (user ==null)
            return "userEmpty";
        List<UserRole> roles = userRoleDao.findByUid(id);
        userDao.delete(user);
        userRoleDao.deleteAll(roles);
        return "ok";
    }

    public String getMyrole(HttpServletRequest request){
        HttpSession session =  request.getSession();
        String userid =  (String) session.getAttribute("userid");
        if(userid==null){
            return null;
        }
        String  arr =   roleDao.getMyRole(userid);

        return arr;
    }
    public List<String> getWorkShops(){
        List<String> list =  roleDao.findWorkshops();
        return list;
    }
    //更新权限
    public String updateRole(HttpServletRequest request){
        //获取数组用  getParameterValues
        String[] roles=request.getParameterValues("roles[]");
        int uid = 0;
        if (!StringUtils.isEmpty(request.getParameter("uid"))){
            uid = Integer.valueOf(request.getParameter("uid"));
        }else{
            String userid = request.getParameter("userid");
            String username = request.getParameter("name");
            User user = userDao.findByPERSONID(userid);
            if (user==null){  //若无此用户保存新账号 ,权限根据传递的角色设定
                user = new User();
                user.setPERSONID(userid);
                user.setPwd("123456");
                user.setUname(username);
                userDao.save(user);
                uid = user.getUid();
            }else{
                uid = user.getUid();
            }
        }
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
                Role role = roleDao.findByWorkshop(s);
                UserRole a = new UserRole();
                a.setUid(uid);
                a.setRid(role.getRid());
                urDao.save(a);
            }
        }
        return "ok";
    }
    public String applyRole(String role,String reason,HttpServletRequest request){
        List<UserRoleModel> urms = getURMs(request);
        if (urms.size()>0){ //有此用户,
            Apply apply = new Apply();
            String oldrid ="";
            for (int i = 0; i<urms.size(); i++){
                String id = String.valueOf(urms.get(i).getRid());
                oldrid  += ","+ id;
            }
            oldrid = oldrid.substring(1);
            apply.setOldrid(oldrid);
            apply.setUid(urms.get(0).getUid());
            apply.setAdate(new Date());
            apply.setRid(roleDao.findByWorkshop(role).getRid());
            apply.setReason(reason);
            apply.setAstate(1);
            applyDao.save(apply);
            return "ok";
        }else {
            return "fail";
        }
    }
    public String doIApply(HttpServletRequest request){
        //如果搜索到有该用户的请求,且状态是 1已申请/2已同意 ,则不允许再次申请
        List<UserRoleModel> urms = getURMs(request);
        int uid = urms.get(0).getUid();
        List<Apply> aps = applyDao.findByUid(uid);
        for (Apply ap : aps){
            if (ap.getAstate()==1 || ap.getAstate()==2){
                return String.valueOf(ap.getAstate());
            }
        }
        return "ok";
    }
    //用户请求的是什么权限
    public String whatIApply(HttpServletRequest request){
        List<UserRoleModel> urms = getURMs(request);
        int uid = urms.get(0).getUid();
        List<Apply> aps = applyDao.findByUid(uid);
        for (Apply ap : aps){
            if (ap.getAstate()==1 || ap.getAstate()==2){
                int rid = ap.getRid();
                Role role = roleDao.findByRid(rid);
                return role.getWorkshop();
            }
        }
        return null;
    }

    /**
     * 注册
     * @param userid
     * @param username
     * @param password
     * @return
     */
    @Override
    @Transactional
    public String register(String userid, String username, String password) {
        System.out.println("注册:"+userid+","+username+","+password);
        User user =  userDao.findByPERSONID(userid);
        if (user!=null){
            return "exist";
        }else {
            user = new User();
            user.setPERSONID(userid);
            user.setPwd(password);
            user.setUname(username);
            userDao.save(user);
            UserRole ur = new UserRole();
            ur.setUid(user.getUid());
            ur.setRid(28);   //28 普通用户 设置角色
            urDao.save(ur);
            return "ok";
        }
    }



    private List<UserRoleModel> getURMs(HttpServletRequest request){
        HttpSession session = request.getSession();
        String userid = (String) session.getAttribute("userid");
        List<UserRoleModel> urms = userDao.findByUserid(userid);
        return urms;
    }
}
