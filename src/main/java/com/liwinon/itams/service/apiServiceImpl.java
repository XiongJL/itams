package com.liwinon.itams.service;

import com.liwinon.itams.dao.primaryRepo.AssetsDao;
import com.liwinon.itams.dao.primaryRepo.EventDao;
import com.liwinon.itams.entity.primay.Assets;
import com.liwinon.itams.entity.primay.Event;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

@Service
public class apiServiceImpl implements apiService{
    @Autowired
    EventDao eventDao;
    @Autowired
    AssetsDao assetsDao;
    @Override
    /**
     * @deprecated
     */
//    public JSONObject getUserOperate(String userid) {
//        Event events = eventDao.findByUname(userid);
//        JSONObject json = new JSONObject();
//        if (events==null){
//            json.accumulate("code",500);
//            json.accumulate("msg","事件尚未创建!");
//            json.accumulate("data",null);
//            return json;
//        }
//        Assets assets =  assetsDao.findByDeviceID(events.getDeviceID());
//        if (assets!=null){
//            FormModel model = new FormModel();
//            model.setAssetsType(assets.getAssetsType());
//            System.out.println(assets);
//            json.accumulate("code",200);
//            json.accumulate("msg","返回数据成功");
//            json.accumulate("data",assets);
//            return json;
//        }
//        json.accumulate("code",500);
//        json.accumulate("msg",events.getDeviceID()+"该设备号未找到资产!");
//        json.accumulate("data",null);
//        return json;
//    }


    /**
     * OA推送表单信息接口
     *  每次OA推送都对应一个表单,不存在则为新建表单, 初始为流程中
     *  存在,但是不为 流程中 状态仍然新建表单.
     *  OA每次结束流程都会回执,并更改流程中为回执状态.
     * @param DeviceID
     * @param UserID   工号
     * @param Event    表单操作类型  (转售/报废)
     * @param FormID    转售/报废表单号
     * @param State     流程中的状态 (流程中,流程终止,已转售,已报废)
     * @return
     */
    public JSONObject getFormInfo(String DeviceID,String UserID,String Event,String FormID,String State){
        JSONObject json = new JSONObject();
        if (StringUtils.isEmpty(DeviceID)||StringUtils.isEmpty(UserID)||StringUtils.isEmpty(Event)
                ||StringUtils.isEmpty(FormID)||StringUtils.isEmpty(State)){
            json.accumulate("code",1);
            json.accumulate("msg","有参数为空");
            return json;
        }
        //查找该资产
        Assets a = assetsDao.findByDeviceID(DeviceID);
        if (a==null){
            json.accumulate("code",2);
            json.accumulate("msg","资产不存在!");
            return json;
        }
        else if ("已转售".equals(a.getAState())||"已报废".equals(a.getAState())||"报废改造".equals(a.getAState())){
            json.accumulate("code",3);
            json.accumulate("msg",a.getAState());
            return json;
        }
        //查找是否是已有事件
        Event e = eventDao.findByFormID(FormID);
        //保存事件
        if (e==null){
            e = new Event();
        }
        e.setDeviceID(DeviceID);
        e.setEvent(Event);
        if ("流程中".equals(State)){
            e.setStartDate(new Date());
        }else{ //除了流程中, 其余都是结束状态
            e.setEndDate(new Date());
        }
        e.setState(State);
        e.setUname(UserID);
        e.setFormID(FormID);
        eventDao.save(e);
        //更改资产转售报废单号
        a.setOutID(FormID);
        a.setAState(State);
        assetsDao.save(a);
        json.accumulate("code",0);
        json.accumulate("msg","成功");

        return json;
    }
}
