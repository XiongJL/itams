package com.liwinon.itams.service;

import net.sf.json.JSONObject;

public interface apiService {
//    JSONObject getUserOperate(String userid);
    JSONObject getFormInfo(String DeviceID,String UserID,String Event,String FormID,String State);
}
