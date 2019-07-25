package com.liwinon.itams.service;

import javax.servlet.http.HttpServletRequest;

public interface operateService {
    String createEvent(String Deviceid, String event, HttpServletRequest request);

    String delAssets(String deviceid);
}
