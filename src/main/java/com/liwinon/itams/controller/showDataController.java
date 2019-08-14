package com.liwinon.itams.controller;

import com.liwinon.itams.dao.primaryRepo.AssetsDao;
import com.liwinon.itams.dao.primaryRepo.UserInfoDao;
import com.liwinon.itams.entity.model.DatasShowModel;
import com.liwinon.itams.entity.primay.Assets;
import com.liwinon.itams.entity.primay.UserInfo;
import com.liwinon.itams.service.showService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class showDataController {
    @Autowired
    UserInfoDao userDao;
    @Autowired
    showService showService;
    @Autowired
    AssetsDao asDao;

    /**
     * @return
     */
    @GetMapping(value = "/itams/datas")
    public String datas() {

        return "showData/showData";
    }

    /**
     * 返回 '数据浏览'的表格数据
     *
     * @param limit
     * @param offset
     * @param content
     * @return
     */
    @GetMapping(value = "/itams/datas/getData")
    @ResponseBody
    public JSONObject getData(int limit, int offset, String content,String type) {
        System.out.println("搜索内容,search:" + content);
        System.out.println("搜索类别,type:"+type);
        //不能以有空的字段来排序
        Sort sort = new Sort(Sort.Direction.ASC, "DeviceID");
        //页码从0开始
        Pageable pageable = PageRequest.of(offset-1, limit, sort);
        JSONObject json = new JSONObject();
        if(type==null||type==""){   //没有查询类型,也就是第一次进入,或者刷新,查询全部数据
            Map<String ,Object> res  = showService.tablesData(pageable);// 部分数据模型
            List<DatasShowModel> datas = (List<DatasShowModel>)res.get("data");
            long total  = (long)res.get("total");   //总条数
            //   System.out.println("datas:" + datas);
            json.accumulate("total", total);
            json.accumulate("rows", datas);
            //JSONObject test = new JSONObject();
           // System.out.println("json:" + json);
            //      test.accumulate("total", 1);
            //    test.accumulate("rows", "[{AssetsID:\"1\",Model:\"ssss\",UserName:\"ssssss\",UserID:\"123\",getTime:\"1920-001\"}]");
            //       System.out.println("test:" + test);
            return json;
        }
        if (content != null && content != "") {
            //
                System.out.println("开始搜索资产");
                Map<String ,Object> res = showService.searchData(content,type,pageable);
                List<DatasShowModel> datas = (List<DatasShowModel>)res.get("data");
                json.accumulate("total", res.get("total"));
                json.accumulate("rows", datas);
                return json;
        }

        return null;
    }

    /**
     * 点击主表的  + 号后,获取该资产的详细信息
     *
     * @param DeviceID
     * @return
     */
    @GetMapping(value = "/itams/datas/getThisData")
    public String getAllData(String DeviceID,Model model) {
        System.out.println("检索子表"+DeviceID);
        Object[] objs = null;
        try{
            objs = new Object[2];
            Assets as = asDao.findByDeviceID(DeviceID);
            UserInfo user = userDao.findByDeviceID(DeviceID);
            if (user == null)
                user = new UserInfo();
            if (as ==null)
                as = new Assets();
            objs[0] = as;
            objs[1] = user;
            List<Assets> list = new ArrayList<>();
            list.add((Assets)objs[0]);
            model.addAttribute("assets",list);
            List<UserInfo> userss = new ArrayList<>();
            userss.add((UserInfo)objs[1]);
            model.addAttribute("users",userss);

            return "showData/sonTable";
        }catch (NullPointerException e){
            return "none";
        }
    }


//    /**
//     * 数据图表
//     *
//     * @param model
//     * @return
//     */
//    @GetMapping(value = "/itams/showData")
//    public String show(Model model) {
//        double total = hdDao.count();
//        double useNum = hdDao.countUse();
//        model.addAttribute("use", useNum);
//        model.addAttribute("notUse", total - useNum);
//        return "showData/dataChart";
//    }
}
