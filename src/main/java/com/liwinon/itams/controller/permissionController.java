package com.liwinon.itams.controller;

import com.liwinon.itams.dao.AssetsDao;
import com.liwinon.itams.dao.HardwareDao;
import com.liwinon.itams.dao.UserDao;
import com.liwinon.itams.dao.UserInfoDao;
import com.liwinon.itams.entity.Assets;
import com.liwinon.itams.entity.DatasShowModel;
import com.liwinon.itams.entity.RoleModel;
import com.liwinon.itams.entity.UserInfo;
import com.liwinon.itams.service.showService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
     * 获取分页的用户角色
     * @param pageable
     * @return
     */
    public  Map<String ,Object>  tablesRole(Pageable pageable){
        Map<String ,Object> result = new HashMap<>();
        Page<String[]> page = userDao.getAllUserRole(pageable);
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
}
