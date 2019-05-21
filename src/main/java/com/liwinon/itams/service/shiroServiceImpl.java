package com.liwinon.itams.service;

import com.liwinon.itams.dao.UserDao;
import com.liwinon.itams.entity.UserRoleModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

public class shiroServiceImpl implements shiroService{
    @Autowired
    UserDao userDao;
    public Set<String> getRolesByUserId(int uid){
        List<UserRoleModel> urms =  userDao.findByUname("xiong");
        return null;
    }
}
