package com.liwinon.itams.controller;

import com.liwinon.itams.entity.primay.Area;
import com.liwinon.itams.entity.primay.Astate;
import com.liwinon.itams.entity.primay.Department;
import com.liwinon.itams.entity.primay.Step;
import com.liwinon.itams.service.selectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *  操作页面的下拉框设置和获取接口
 */
@Controller
@RequestMapping("/itams/select")
public class selectController {
    @Autowired
    selectService selectService;
    //进入修改页面
    @GetMapping
    public String modifySelect(){
        return "role/select";
    }
    //获取所有资产状态
    @GetMapping("/Astate")
    @ResponseBody
    public List<Astate> getAllAstate(){
        return selectService.getAllAstate();
    }
    //获取所有区域
    @GetMapping("/Area")
    @ResponseBody
    public List<Area> getAllArea(){
        return selectService.getAllArea();
    }
    //获取所有部门
    @GetMapping("/Department")
    @ResponseBody
    public List<Department> getAllDeparment(){
        return selectService.getAllDepartment();
    }
    //获取所有工序
    @GetMapping("/Step")
    @ResponseBody
    public List<Step> getAllStep(){
        return selectService.getAllStep();
    }

    //更改值
    @PostMapping("/changeValues")
    @ResponseBody
    public String changeValues(HttpServletRequest request){
        return selectService.changeValues(request);
    }

    //删除
    @GetMapping("/delValue")
    @ResponseBody
    public String del(String type,int id){
        return selectService.del(type,id);
    }
}
