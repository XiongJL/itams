package com.liwinon.itams.controller;

import com.liwinon.itams.service.apiService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 对外开放数据接口
 */
@RestController
@RequestMapping(value = "/itams/api")
public class apiController {

    @Autowired
    apiService api;
//    @GetMapping(value = "/getUserOperate")
//    public JSONObject getUserOperate(String userid){
//     //   return api.getUserOperate(userid);
//    }



    /**
     * OA推送表单信息接口
     * @param DeviceID
     * @param UserID   工号
     * @param Event    表单操作类型  (转售/报废)
     * @param FormID    转售/报废表单号
     * @param State     流程中的状态 (流程中,流程终止,已转售,已报废)
     * @return
     */
    @GetMapping(value="/getFormInfo")
    public JSONObject getFormInfo(String DeviceID,String UserID,String Event,String FormID,String State){


        return api.getFormInfo(DeviceID, UserID, Event, FormID, State);
    }
}
