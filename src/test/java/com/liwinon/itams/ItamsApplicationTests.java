package com.liwinon.itams;

import com.liwinon.itams.dao.AssetsDao;
import com.liwinon.itams.dao.UserDao;
import com.liwinon.itams.dao.UserInfoDao;
import com.liwinon.itams.entity.Role;
import com.liwinon.itams.entity.User;
import com.liwinon.itams.entity.UserRoleModel;
import com.liwinon.itams.service.searchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItamsApplicationTests {
    @Autowired
    AssetsDao asDao;
    @Autowired
    searchService search;
    @Autowired
    UserDao userDao;
    @Test
    public void contextLoads() {
        System.out.println(Utils.isContainChinese("熊健了 15646546"));
    }
    @Test
    public void export(){
       List<UserRoleModel> ms  = userDao.findByUname("xiong");
    }
}
