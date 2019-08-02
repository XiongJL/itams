package com.liwinon.itams.service;

import javax.servlet.http.HttpServletRequest;

public interface hardwareService {
    //保存Hardware
    String saveHardware(HttpServletRequest request);

    //解析HardwareExcel文件
    String resolveExcel(String[] columns,String filePath,int type);
}
