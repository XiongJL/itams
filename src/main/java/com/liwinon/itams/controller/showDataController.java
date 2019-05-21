package com.liwinon.itams.controller;

import com.liwinon.itams.dao.AssetsDao;
import com.liwinon.itams.dao.HardwareDao;
import com.liwinon.itams.dao.UserInfoDao;
import com.liwinon.itams.entity.*;
import com.liwinon.itams.service.showService;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Controller
public class showDataController {
    @Autowired
    HardwareDao hdDao;
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
        System.out.println("页面大小,limit:" + limit);
        System.out.println("页码,offset:" + offset);
        System.out.println("搜索内容,search:" + content);
        System.out.println("搜索类别,type:"+type);
        //不能以有空的字段来排序
        Sort sort = new Sort(Sort.Direction.ASC, "AssetsID");
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
            JSONObject test = new JSONObject();
           // System.out.println("json:" + json);
            //      test.accumulate("total", 1);
            //    test.accumulate("rows", "[{AssetsID:\"1\",Model:\"ssss\",UserName:\"ssssss\",UserID:\"123\",getTime:\"1920-001\"}]");
            //       System.out.println("test:" + test);
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
     * 点击主表的  + 号后,获取该资产的详细信息
     *
     * @param AssetsID
     * @return
     */
    /*@GetMapping(value = "/itams/datas/getAllData")
    @ResponseBody
    public JSONObject getAllData(String AssetsID) {
        //全部数据模型
        DatasModel data = new DatasModel();//返回结果数据模型
        Hardware hd = hdDao.findByAssetsID(AssetsID);
        if (hd==null)
            return null;
        data.setAssetsID(hd.getAssetsID());
        if(hd.getITtype()!=null)
            data.setAssetsType(hd.getITtype());
        if(hd.getBrand()!=null)
            data.setBrand(hd.getBrand());
        if(hd.getCPU()!=null)
            data.setCPU(hd.getCPU());
        if(hd.getRAM()!=null)
            data.setRAM(hd.getRAM());
        if(hd.getHDD1()!=null)
            data.setHDD1(hd.getHDD1());
        if(hd.getHDD2()!=null)
            data.setHDD2(hd.getHDD2());
        if(hd.getDepartment()!=null)
            data.setDepartment(hd.getDepartment());
        if(hd.getWiredMAC1()!=null)
            data.setWiredMAC1(hd.getWiredMAC1());
        if(hd.getWiredMAC2()!=null)
            data.setWiredMAC2(hd.getWiredMAC2());
        if(hd.getWirelessMAC()!=null)
            data.setWirelessMAC(hd.getWirelessMAC());
        if(hd.getLocation()!=null)
            data.setLocation(hd.getLocation());
            data.setState(hd.getState());
        if(hd.getPurchaseDate()!=null)
            data.setPurchaseDate(hd.getPurchaseDate());
        if(hd.getStaff()!=null)
            data.setStaff(hd.getStaff());
        if(hd.getEntryDate()!=null)
            data.setEntryDate(hd.getEntryDate());
        if(hd.getDepartment()!=null)
            data.setDepartment(hd.getDepartment());
        if(hd.getRemark()!=null) {
            System.out.println(hd.getRemark());
            data.setRemark(hd.getRemark());
        }
        UserInfo user = userDao.findByAssetsID(hd.getAssetsID());
        if (user != null) { //该资产有使用者
            data.setUserName(user.getUserName());
            data.setUserID(user.getUserID());
            data.setTelephone(user.getTelephone());
            data.setLphone(user.getLphone());
            data.setGetTime(user.getGetTime());
            data.setUserDepartment(user.getUserDepartment());
        }
//        JSONObject json = new JSONObject();
//        json.accumulate("data",data);
        JSONObject json = JSONObject.fromObject(data.toString());
        System.out.println(json);
        return json;

    }*/

    @GetMapping(value = "/itams/datas/getThisData")
    public String getAllData(String AssetsID,Model model) {
        System.out.println("检索子表"+AssetsID);
        Object[] objs = null;
        try{
            objs = new Object[2];
            Assets as = asDao.findByAssetsID(AssetsID);
            UserInfo user = userDao.findByAssetsID(AssetsID);;
            if (user==null)
                user = new UserInfo();
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


    /**
     * 数据图表
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/itams/showData")
    public String show(Model model) {
        double total = hdDao.count();
        double useNum = hdDao.countUse();
        model.addAttribute("use", useNum);
        model.addAttribute("notUse", total - useNum);
        return "showData/dataChart";
    }
}
