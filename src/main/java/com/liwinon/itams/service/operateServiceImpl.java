package com.liwinon.itams.service;

import com.liwinon.itams.dao.primaryRepo.AssetsDao;
import com.liwinon.itams.dao.primaryRepo.EventDao;
import com.liwinon.itams.entity.primay.Assets;
import com.liwinon.itams.entity.primay.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class operateServiceImpl implements operateService {
    @Autowired
    EventDao eventDao;
    @Autowired
    AssetsDao assetsDao;

    /**
     *      暂时不使用该方法推送OA单
     * 保存事件,并通过webservice发起单据
     * @param Deviceid
     * @param event
     * @param request
     * @return
     */
    @Override
    @Transactional
    public String createEvent(String Deviceid, String event, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        List<Event> es = eventDao.findByDeviceIDAndState(Deviceid);
        if (es.size()>0){
            for (Event event2:es){
                if ("流程中".equals(event2.getState())){
                    return "正在流程中";
                }
                if ("已转售".equals(event2.getState())){
                    return "已转售";
                }
                if ("已报废".equals(event2.getState())){
                    return "已报废";
                }
                if ("报废改造".equals(event2.getState())){
                    return "报废改造";
                }
            }
        }
        if (username!=null){
            //事件保存在OA推送后执行,这里只判断事件是否在流程中
            return "ok";
        }
        return "操作失败!请尝试重新登录";
    }

    /**
     * 根据设备ID删除资产
     * @param deviceid
     * @return
     */
    @Override
    @Transactional
    public String delAssets(String deviceid) {
        Assets assets =  assetsDao.findByDeviceID(deviceid);
        if (assets==null){
            return "资产已经不存在!";
        }
        assetsDao.delete(assets);
        return "ok";
    }
}
