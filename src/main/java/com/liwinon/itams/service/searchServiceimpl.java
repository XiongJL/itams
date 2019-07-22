package com.liwinon.itams.service;

import com.liwinon.itams.dao.primaryRepo.AssetsDao;
import com.liwinon.itams.dao.primaryRepo.UserInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class searchServiceimpl implements searchService {

    @Autowired
    UserInfoDao userDao;
    @Autowired
    AssetsDao asDao;

//    /**
//     * 联想搜索
//     *
//     * @param uname 被联想的词
//     * @return
//     */
//    public List<String[]> lianxiang(String uname) {
//        List<UserInfo> list = userDao.getLikeUname(uname);
//        List<String[]> result = null;
//        if (list.size() > 0) {
//            result = new ArrayList<>();
//            for (UserInfo u : list) {
//                result.add(new String[]{u.getAssetsID(), u.getUserName(), u.getUserID()});
//            }
//        }
//        List<Hardware> hds = hdDao.getLikeUname(uname);
//        if(list.size()>0){
//            if(result==null)
//                result = new ArrayList<>();
//            for(Hardware hd : hds){
//                result.add(new String[]{hd.getAssetsID(),"", ""});
//            }
//        }
//
//        return result;
//    }

    /**
     * 搜索用户填入的内容
     * @param
     * @return
     */
//    public Object[] search(String input) {
//        Object[] objs;
//        if (input.indexOf("-") != -1) {  //存在  -  ,则假定为搜索资产编号
//            objs = new Object[2];
//            Hardware hd = hdDao.findByAssetsID(input);
//            UserInfo user =null;
//            if("0".equals(hd.getState())){  //未被使用
//                user = new UserInfo();
//            }else{
//                user = userDao.findByAssetsID(input);
//            }
//
//            objs[0] = hd;
//            objs[1] = user;
//        } else {    //则为搜索工号
//            List<UserInfo> users = userDao.findByUserID(input);
//            List<Hardware> hds = new ArrayList<>();
//            for(UserInfo info : users){
//                Hardware hd = hdDao.findByAssetsID(info.getAssetsID());
//                hds.add(hd);
//            }
//            objs = new Object[2];
//            objs[0] = hds;
//            objs[1] = users;
//        }
//
//        return objs;
//    }

    //导出搜索的结果到Excel
    public List<String[]> getdata(String content , String type){
        List<String[]> data = new ArrayList<>(); //初始化数据
        data.add(new String[]{"资产类型", "资产类别", "资产编号", "设备编号", "出厂编号", "资产名称", "规格型号", "供应商",
                "厂区", "区域", "详细位置", "部门", "工序", "责任人", "责任人工号", "性能状态", "资产状态",
                "需求报告编号", "技术协议号", "合同号", "入厂日期", "收货单号", "验收单号", "报废/转售单号","备注"}); //第一行
        if(content==null && type ==null){ //导出的是全部数据
            List<String> values =  asDao.expotAll();
            for(String value : values){
                String[] val = value.split(",");
                data.add(val);
            }
        }else{  //导出的是搜索的数据

            String[] strs = content.split(",");
            if("1".equals(type)){  //通过资产ID搜索
                String value = null;
                for (String s : strs) { //搜索内容
                    s = s.trim();
                    value = asDao.exportSelectByAsID(s);
                    String[] values = value.split(",");
                    data.add(values);
                }
                System.out.println(data);
            }
            else if("2".equals(type)){  //通过工号搜索
                List<String> value = null;
                for (String s : strs){ //搜索内容
                    s = s.trim();
                    value = userDao.exportSelectByUserID(s);
                    for(String val : value){
                        String[] values = val.split(",");
                        data.add(values);
                    }
                }
            }
            else if ("3".equals(type)){  //通过设备号搜索
                String value = null;
                for (String s : strs) { //搜索内容
                    s = s.trim();
                    value = asDao.exportByDeviceID(s);
                    String[] values = value.split(",");
                    data.add(values);
                }
            }
            else if ("4".equals(type)){   //通过日期查询
                String[] dates = content.split("/");
                Date date1 = Date.valueOf(dates[0]);
                Date date2 = Date.valueOf(dates[1]);
                List<String> values = asDao.exportByDate(date1,date2);
                for(String value : values){
                    String[] val = value.split(",");
                    data.add(val);
                }
            }else if ("8".equals(type)){ //通过资产状态查询
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
                List<String> values = asDao.exportByAState(l1,l2,l3,l4);
                for(String value : values){
                    String[] val = value.split(",");
                    data.add(val);
                }
            }
            else {
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
                List<String> values = new ArrayList<>();
                if ("5".equals(type)){//通过详细位置查询
                    values = asDao.exportByLocation(l1,l2,l3,l4,l5);
                }
                if ("6".equals(type)){ //根据资产类别搜索
                    values = asDao.exportCategory(l1,l2,l3,l4,l5);
                }
                if ("7".equals(type)){  ////根据责任人搜索
                    values = asDao.exportPerson(l1,l2,l3,l4,l5);
                }
                for(String value : values){
                    String[] val = value.split(",");
                    data.add(val);
                }
            }
        }
        return data;
    }


}
