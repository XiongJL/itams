package com.liwinon.itams.service;

import com.liwinon.itams.dao.AssetsDao;
import com.liwinon.itams.dao.HardwareDao;
import com.liwinon.itams.dao.UserInfoDao;
import com.liwinon.itams.entity.Assets;
import com.liwinon.itams.entity.DatasShowModel;
import com.liwinon.itams.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class showServiceImpl implements showService {
    @Autowired
    HardwareDao hdDao;
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
            user = userDao.findByAssetsID(as.getAssetsID());
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
    public List<DatasShowModel>  searchData(String content,String type){
        List<DatasShowModel> datas = new ArrayList<>();
        DatasShowModel model =null;
        UserInfo user =null;
        String[] strs = content.split(",");
        for (String s : strs){ //搜索内容
            s = s.trim();
            Assets as =null ;
            if("1".equals(type)){  //通过资产ID搜索
                as  = asDao.findByAssetsID(s);
                System.out.println(as);
                if(as==null)  //没有资产,不保存因此前端不会显示本次搜索的用户或者资产
                    continue;
                user = userDao.findByAssetsID(as.getAssetsID());
                model = setmodel(as,user);
                datas.add(model);
            }
            if("2".equals(type)){  //通过工号搜索
                List<UserInfo> users = userDao.findByUserID(s);
                System.out.println(users.size());
                for (UserInfo u:users){
                    if(u==null)  //没有找到
                        continue;
                    as = asDao.findByAssetsID(u.getAssetsID());
                    if (as==null)
                        continue;
                    model = setmodel(as,u);
                    System.out.println("model:"+model);
                    datas.add(model);
                }
            }
            if ("3".equals(type)){ //通过设备号查询
                as  = asDao.findByDeviceID(s);
                System.out.println(as);
                if(as==null)  //没有资产,不保存因此前端不会显示本次搜索的用户或者资产
                    continue;
                user = userDao.findByAssetsID(as.getAssetsID());
                model = setmodel(as,user);
                datas.add(model);
            }
        }

        return datas;
    }

    /**
     * 用以返回一个设值过后的数据类型
     * @param as
     * @param user
     * @return
     */
    private DatasShowModel setmodel(Assets as,UserInfo user){
        DatasShowModel model = new DatasShowModel();
        model = new DatasShowModel();
        if (as!=null){
            model.setAssetsType(as.getAssetsType());
            model.setAssetsCategory(as.getAssetsCategory());
            model.setDeviceID(as.getDeviceID());
            model.setAssetsID(as.getAssetsID());
            model.setFactoryID(as.getFactoryID());
            model.setAssetsName(as.getAssetsName());
            model.setModel(as.getModels());
            model.setSupplier(as.getSupplier());
        }
        if (user != null) { //该资产有使用者
            model.setGetTime(user.getGetTime());
            model.setUserID(user.getUserID());
            model.setUserName(user.getUserName());
        }
        return model;
    }
}
