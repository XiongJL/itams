package com.liwinon.itams.service;

import com.liwinon.itams.dao.HardwareDao;
import com.liwinon.itams.dao.UserInfoDao;
import com.liwinon.itams.entity.Hardware;
import com.liwinon.itams.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class searchServiceimpl implements searchService {

    @Autowired
    UserInfoDao userDao;
    @Autowired
    HardwareDao hdDao;

    /**
     * 联想搜索
     *
     * @param uname 被联想的词
     * @return
     */
    public List<String[]> lianxiang(String uname) {
        List<UserInfo> list = userDao.getLikeUname(uname);
        List<String[]> result = null;
        if (list.size() > 0) {
            result = new ArrayList<>();
            for (UserInfo u : list) {
                result.add(new String[]{u.getAssetsID(), u.getUserName(), u.getUserID()});
            }
        }
        List<Hardware> hds = hdDao.getLikeUname(uname);
        if(list.size()>0){
            if(result==null)
                result = new ArrayList<>();
            for(Hardware hd : hds){
                result.add(new String[]{hd.getAssetsID(),"", ""});
            }
        }

        return result;
    }

    /**
     * 搜索用户填入的内容
     * @param input
     * @return
     */
    public Object[] search(String input) {
        Object[] objs;
        if (input.indexOf("-") != -1) {  //存在  -  ,则假定为搜索资产编号
            objs = new Object[2];
            Hardware hd = hdDao.findByAssetsID(input);
            UserInfo user =null;
            if("0".equals(hd.getState())){  //未被使用
                user = new UserInfo();
            }else{
                user = userDao.findByAssetsID(input);
            }

            objs[0] = hd;
            objs[1] = user;
        } else {    //则为搜索工号
            List<UserInfo> users = userDao.findByUserID(input);
            List<Hardware> hds = new ArrayList<>();
            for(UserInfo info : users){
                Hardware hd = hdDao.findByAssetsID(info.getAssetsID());
                hds.add(hd);
            }
            objs = new Object[2];
            objs[0] = hds;
            objs[1] = users;
        }

        return objs;
    }



}
