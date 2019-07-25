package com.liwinon.itams.controller;

import com.liwinon.itams.dao.primaryRepo.AssetsDao;
import com.liwinon.itams.dao.secondRepo.SapDao;
import com.liwinon.itams.entity.primay.Assets;
import com.liwinon.itams.service.hardwareService;
import com.liwinon.itams.service.operateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
/**
 * 信息接口层
 */
public class OperateInfoController {
    @Autowired
    AssetsDao assetsDao;
    @Autowired
    hardwareService hdService;
    @Autowired
    SapDao sapDao;
    @Autowired
    operateService operateService;

    /**
     *  判断AssetsID是否存在
     * @param asid
     * @return
     */
    @GetMapping(value = "/itams/operate/asidExist")
    public String AsIdExist(String asid){
        System.out.println(asid);
        Assets as = assetsDao.findByAssetsID(asid);
        if (as!=null){
            return "exist";
        }
        return null;
    }
    /**
     *  deviceId
     * @param deviceId 设备号
     * @return
     */
    @GetMapping(value = "/itams/operate/deviceExist")
    public String DeviceIdExist(String deviceId){
        System.out.println(deviceId);
        Assets as = assetsDao.findByDeviceID(deviceId);
        if (as!=null){
            return "exist";
        }
        return null;
    }


//    @GetMapping(value="/itams/operate/type")
//    public List<String> allType(){
//        List<String> list = infoDao.findTypes();
//        return list;
//    }

//    @GetMapping(value="/itams/operate/Staff")
//    public List<String> allStaff(){
//        List<String> list = staffDao.findAllStaff();
//        return list;
//    }

    /**
     * 根据类别查询其的品牌
     * @param type
     * @return
     */
//    @GetMapping(value = "/itams/operate/Brands")
//    public List<String> brands(String type){
//        List<String> list = infoDao.findBrandByType(type);
//        return list;
//    }

//    @GetMapping(value = "/itams/operate/Models")
//    public String[] models(String type,String brand){
//        String res = infoDao.findModelsByTypeAndBrand(type,brand);
//        if (res!=null && res!=""){
//            String[] result = res.split("_");
//            return result;
//        }
//        return null;
//    }

    /**
     * 通过工号获取姓名
     * @param userid
     * @return
     */
    @GetMapping(value="/itams/operate/getName")
    public String getName(String userid){
        return sapDao.findNameByUserId(userid);
    }

    /**
     * 新建一个资产
     * @param request
     * @return
     */
    @PostMapping(value="/itams/operate/saveHardware")
    public String saveHardware(HttpServletRequest request){
        return hdService.saveHardware(request);
    }

    /**
     * 保存用户的操作,返回状态码
     * @param Deviceid 设备编号
     * @param event 事件 转售 、报废
     * @return
     */
    @GetMapping(value = "/itams/operate/event")
    public  String event(String Deviceid,String event,HttpServletRequest request){
            return operateService.createEvent(Deviceid, event, request);

    }

    /**
     * 删除资产
     */
    @GetMapping(value = "/itams/operate/del")
    public String del(String Deviceid){
        return operateService.delAssets(Deviceid);
    }
}
