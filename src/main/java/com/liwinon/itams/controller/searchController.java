package com.liwinon.itams.controller;

import com.liwinon.itams.dao.primaryRepo.UserInfoDao;
import com.liwinon.itams.service.searchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin
public class searchController {
    @Autowired
    searchService search;
    @Autowired
    UserInfoDao userDao;
    @GetMapping(value = "/itams/search")
    public String search(){
        return "search/search";
    }
  //  @GetMapping(value = "/itams/search/{input}")
//    public String searchTo(@PathVariable String input,Model model){
//        System.out.println("开始搜索"+input);
//        //通过正则判断是否是   熊健淋:S1941646 的格式
//        String searchText = input;
//        if(input.indexOf(":")!=-1){  //存在  :
//            String[] strs =  input.split(":");
//            searchText = strs[1];  //只根据用户id搜索
//        }
//        Object[] objs = null;
//        try{
//            objs = search.search(searchText);
//            if(objs[0]==null){
//                objs[0] = new ArrayList<>();
//            }
//            if(objs[1]==null){
//                objs[1] = new UserInfo();
//            }
////            System.out.println((List<Hardware>)objs[0]);
//       //     System.out.println((List<UserInfo>)objs[1]);
//            if (objs[0] instanceof Hardware) {//如果返回的对象只有一个,不是集合
//                List<Hardware> list = new ArrayList<>();
//                list.add((Hardware)objs[0]);
//                model.addAttribute("hds",list);
//            }else{
//                model.addAttribute("hds",(List<Hardware>)objs[0]);
//            }
//            if(objs[1] instanceof UserInfo){  //如果返回的对象只有一个,不是集合
//                List<UserInfo> userss = new ArrayList<>();
//                userss.add((UserInfo)objs[1]);
//                model.addAttribute("users",userss);
//            }else {
//                model.addAttribute("users",(List<UserInfo>)objs[1]);
//            }
//        //    model.addAttribute("hds",(List<Hardware>)objs[0]);
//          //  model.addAttribute("users",(List<UserInfo>)objs[1]);
//            return "search/result";
//        }catch (NullPointerException e){
//            System.out.println("请根据提示搜索");
//            model.addAttribute("err","none");
//            return "none";
//        }
//
//
//
//    }
//    @GetMapping(value = "/itams/search/lianxiang")
//    public String lianxiang(String uname, Model model){
//        System.out.println(uname);
//        if(uname==null||uname==""){
//            return "none";
//        }
//        List<String[]> list =  search.lianxiang(uname);
//        if(list==null){
//            return "none";
//        }
//        List<String> res = new ArrayList<>();
//        for(String[] str : list){
//            if(str[0].indexOf(uname.toUpperCase())!=-1){
//                res.add(str[0]);
//            }else if(str[1]!=""&&str[2]!=""){
//                res.add(str[1]+":"+str[2]);
//            }
//
//        }
//        model.addAttribute("res",res);
//        return "search/lianxiang";
//    }

//    @GetMapping(value="/test")
//    public String ssd(Model model){
//        Hardware hd =  hwDao.findByAssetsID("HL-I-00001");
//        UserInfo user = userDao.findByUserID("1902268014");
//        System.out.println(hd);
//        System.out.println(user);
//        model.addAttribute("hd",hd);
//        model.addAttribute("user",user);
//        return "search/result";
//    }
}
