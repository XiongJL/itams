package com.liwinon.itams.service;

import com.liwinon.itams.entity.model.RoleModel;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface userService {
    JSONObject getrole(int limit, int offset, String content, String type);
    String getMyrole(HttpServletRequest request);
    List<String> getWorkShops();
    String updateRole(HttpServletRequest request);
    String applyRole(String role,String reason,HttpServletRequest request);
    String doIApply(HttpServletRequest request);
    String whatIApply(HttpServletRequest request);

    String register(String userid, String username, String password);

    RoleModel getRoleByIdAndUser(String content);
    String delUser(int id);
}
