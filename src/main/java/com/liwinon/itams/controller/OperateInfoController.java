package com.liwinon.itams.controller;

import com.liwinon.itams.dao.HardwareDao;
import com.liwinon.itams.dao.HardwareInfoDao;
import com.liwinon.itams.dao.SapDao;
import com.liwinon.itams.dao.StaffDao;
import com.liwinon.itams.entity.Hardware;
import com.liwinon.itams.entity.HardwareInfo;
import com.liwinon.itams.service.hardwareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Null;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;

@RestController
/**
 * 信息接口层
 */
public class OperateInfoController {
    @Autowired
    HardwareDao hdDao;
    @Autowired
    HardwareInfoDao infoDao;
    @Autowired
    StaffDao staffDao;
    @Autowired
    hardwareService hdService;
    @Autowired
    SapDao sapDao;

    /**
     *  判断AssetsID是否存在
     * @param asid
     * @return
     */
    @GetMapping(value = "/itams/operate/asidExist")
    public String AsIdExist(String asid){
        System.out.println(asid);
        Hardware hd = hdDao.findByAssetsID(asid);
        if (hd!=null){
            return "exist";
        }
        return null;
    }


    @GetMapping(value="/itams/operate/type")
    public List<String> allType(){
        List<String> list = infoDao.findTypes();
        return list;
    }

    @GetMapping(value="/itams/operate/Staff")
    public List<String> allStaff(){
        List<String> list = staffDao.findAllStaff();
        return list;
    }

    /**
     * 根据类别查询其的品牌
     * @param type
     * @return
     */
    @GetMapping(value = "/itams/operate/Brands")
    public List<String> brands(String type){
        List<String> list = infoDao.findBrandByType(type);
        return list;
    }

    @GetMapping(value = "/itams/operate/Models")
    public String[] models(String type,String brand){
        String res = infoDao.findModelsByTypeAndBrand(type,brand);
        if (res!=null && res!=""){
            String[] result = res.split("_");
            return result;
        }
        return null;
    }

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


}
