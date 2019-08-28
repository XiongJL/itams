package com.liwinon.itams.service;

import com.liwinon.itams.dao.primaryRepo.AssetsDao;
import com.liwinon.itams.dao.primaryRepo.UserInfoDao;
import com.liwinon.itams.entity.primay.Assets;
import com.liwinon.itams.entity.model.DatasShowModel;
import com.liwinon.itams.entity.primay.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class showServiceImpl implements showService {
    @Autowired
    UserInfoDao userDao;
    @Autowired
    AssetsDao asDao;

    /**
     * 获取重要数据
     * @param pageable
     * @return
     */
    public  Map<String ,Object>  tablesData(Pageable pageable){
        Map<String ,Object> result = new HashMap<>();
        Page<Assets> page = asDao.findMyAll(pageable);
       // System.out.println("总数total:"+page.getTotalElements());
        result.put("total",page.getTotalElements());
        List<Assets> list = page.getContent();
        System.out.println(list.size());
        UserInfo user;
        List<DatasShowModel> datas = new ArrayList<>();// 部分数据模型
        DatasShowModel data;
        for (Assets as : list) {    //
            user = userDao.findByDeviceID(as.getDeviceID());
            if (user==null){
                user = new UserInfo();
            }
            data = setmodel(as,user);  //保存数据
            //   System.out.println("data:" + data);
            datas.add(data); //添加数据
        }
        result.put("data",datas);
        return result;
    }

    /**
     * 获取搜索的数据
     * @param content
     * @param type
     * @return
     */
    public Map<String ,Object>  searchData(String content,String type,Pageable pageable){
        System.out.println("进入搜索");
        Map<String ,Object> result = new HashMap<>();
        List<DatasShowModel> datas = new ArrayList<>();
        DatasShowModel model =null;
        UserInfo user =null;
        String[] strs = content.split(",");
        if("4".equals(type)){ //通过日期搜索
            String[] dates = content.split("/");
            Date date1 = Date.valueOf(dates[0]);
            Date date2 = Date.valueOf(dates[1]);
            Page<Assets> page = asDao.findBetweenDate(date1,date2,pageable);
            List<Assets> list = page.getContent();
            for(Assets as : list){
                user = userDao.findByDeviceID(as.getDeviceID());
                model = setmodel(as,user);
                datas.add(model);
            }
            result.put("total",page.getTotalElements());
        }else if ("5".equals(type)){
             //通过详细位置查询
            Page<Assets> page = getAssets(pageable, strs,type);
            List<Assets> list = page.getContent();
            for(Assets as : list){
                user = userDao.findByDeviceID(as.getDeviceID());
                model = setmodel(as,user);
                datas.add(model);
            }
            result.put("total",page.getTotalElements());

        }else if ("6".equals(type)){
            //通过资产类别查询
            Page<Assets> page = getAssets(pageable, strs,type);
            List<Assets> list = page.getContent();
            for(Assets as : list){
                user = userDao.findByDeviceID(as.getDeviceID());
                model = setmodel(as,user);
                datas.add(model);
            }
            result.put("total",page.getTotalElements());
        }else if ("7".equals(type)){
            //通过责任人查询
            Page<Assets> page = getAssets(pageable, strs,type);
            List<Assets> list = page.getContent();
            for(Assets as : list){
                user = userDao.findByDeviceID(as.getDeviceID());
                model = setmodel(as,user);
                datas.add(model);
            }
            result.put("total",page.getTotalElements());
        }else if ("8".equals(type)){
            //通过资产状态查询
            String l1= "###",l2= "###",l3= "###",l4= "###";
            int i  = 1;
            for (String s : strs){
                s = s.trim();
                if(i ==1)
                    l1 = s;
                if(i==2)
                    l2 = s;
                if (i==3)
                    l3 = s;
                if (i==4)
                    l4 = s;
                i++;
            }
            Page<Assets> page = asDao.findBtAState(l1,l2,l3,l4,pageable);
            List<Assets> list = page.getContent();
            for(Assets as : list){
                user = userDao.findByDeviceID(as.getDeviceID());
                model = setmodel(as,user);
                datas.add(model);
            }
            result.put("total",page.getTotalElements());
        }else if ("9".equals(type)){
            //根据资产名模糊搜索
            Page<Assets> page = getAssets(pageable, strs,type);
            List<Assets> list = page.getContent();
            for(Assets as : list){
                user = userDao.findByDeviceID(as.getDeviceID());
                model = setmodel(as,user);
                datas.add(model);
            }
            result.put("total",page.getTotalElements());
        }
        else{
            for (String s : strs){ //搜索内容
                s = s.trim();
                Assets as =null ;
                if("1".equals(type)){  //通过资产ID搜索
                    as  = asDao.findByAssetsID(s);
                    if(as==null)  //没有资产,不保存因此前端不会显示本次搜索的用户或者资产
                        continue;
                    user = userDao.findByDeviceID(as.getDeviceID());
                    model = setmodel(as,user);
                    datas.add(model);
                }
                if("2".equals(type)){  //通过工号搜索
                    List<UserInfo> userss = userDao.findByUserID(s);
                    for (UserInfo u:userss){
                        if(u==null)  //没有找到
                            continue;
                        as = asDao.findByDeviceID(u.getDeviceID());
                        if (as==null)
                            continue;
                        model = setmodel(as,u);
                        datas.add(model);
                    }
                }
                if ("3".equals(type)){ //通过设备号查询
                    as  = asDao.findByDeviceID(s);
                    if(as==null)  //没有资产,不保存因此前端不会显示本次搜索的用户或者资产
                        continue;
                    user = userDao.findByDeviceID(as.getDeviceID());
                    model = setmodel(as,user);
                    datas.add(model);
                }

            }
            result.put("total",datas.size());
        }
        result.put("data",datas);
        return result;
    }

    private Page<Assets> getAssets(Pageable pageable, String[] strs,String type) {
        String l1= "###",l2= "###",l3= "###",l4= "###",l5 = "###"; //默认### 在搜索时则搜不到任何内容
        int i  = 1;
        for (String s : strs){
            s = s.trim();
            if(i ==1)
                l1 = s;
            if(i==2)
                l2 = s;
            if (i==3)
                l3 = s;
            if (i==4)
                l4 = s;
            if(i==5)
                l5 = s;
            i++;
        }
        if ("5".equals(type)){
            return asDao.findByLocation(l1,l2,l3,l4,l5,pageable);
        }
        if ("6".equals(type)){
            return asDao.findByCategory(l1,l2,l3,l4,l5,pageable);
        }
        if ("7".equals(type)){
            return asDao.findByPerson(l1,l2,l3,l4,l5,pageable);
        }
        if ("9".equals(type)){
            return asDao.findByAssetsName(l1,l2,l3,l4,l5,pageable);
        }
        return  null;
    }

    /**
     * 用以返回一个设值过后的数据类型
     * @param as
     * @param user
     * @return
     */
    private DatasShowModel setmodel(Assets as,UserInfo user){
        DatasShowModel model = new DatasShowModel();
        if (as!=null){
            model.setAssetsType(as.getAssetsType());
            model.setAssetsCategory(as.getAssetsCategory());
            model.setDeviceID(as.getDeviceID());
            model.setAssetsID(as.getAssetsID());
            model.setFactoryID(as.getFactoryID());
            model.setAssetsName(as.getAssetsName());
            model.setModel(as.getModels());
            model.setSupplier(as.getSupplier());
            if (user != null) { //该资产有使用者
                if (user.getGetTime()==null){
                    model.setGetTime(Date.valueOf("0001-01-01").toString());
                }else {
                    model.setGetTime(user.getGetTime().toString());
                }
                model.setUserID(user.getUserID());
                model.setUserName(user.getUserName());
            }
        }

        return model;
    }
}
