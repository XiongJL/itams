package com.liwinon.itams;

import com.liwinon.itams.dao.primaryRepo.AssetsDao;
import com.liwinon.itams.dao.primaryRepo.UserDao;
import com.liwinon.itams.service.apiService;
import com.liwinon.itams.service.searchService;
import com.liwinon.itams.utils.ReqWebservice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItamsApplicationTests {
    @Autowired
    AssetsDao asDao;
    @Autowired
    searchService search;
    @Autowired
    UserDao userDao;
    @Autowired
    apiService api;
    @Autowired
    ReqWebservice req;

    @Test
    public void contextLoads() {
//        try {
//      //      req.postData("123","DL-P-00159");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

}
