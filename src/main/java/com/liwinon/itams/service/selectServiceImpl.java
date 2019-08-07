package com.liwinon.itams.service;

import com.liwinon.itams.dao.primaryRepo.*;
import com.liwinon.itams.entity.primay.*;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

@Service
public class selectServiceImpl implements selectService {
    @Autowired
    AstateDao astateDao;
    @Autowired
    PstateDao pstateDao;
    @Autowired
    AreaDao areaDao;
    @Autowired
    DepartmentDao departmentDao;
    @Autowired
    StepDao stepDao;
    @Autowired
    EventDao eventDao;
    @Autowired
    TypeDao typeDao;
    @Override
    public List<Astate> getAllAstate() {
        return astateDao.findAll();
    }

    @Override
    public List<Pstate> getAllPstate() {
        return pstateDao.findAll();
    }

    @Override
    public List<Area> getAllArea() {
        return areaDao.findAll();
    }

    @Override
    public List<Department> getAllDepartment() {
        return departmentDao.findAll();
    }

    @Override
    public List<Step> getAllStep() {
        return stepDao.findAll();
    }

    @Override
    public List<Type> getAllType() {
        return typeDao.findAll();
    }

    @Override
    @Transactional
    public String changeValues(HttpServletRequest request) {
        String type = request.getParameter("select");
        if (StringUtils.isEmpty(type)){
            return "类型错误.";
        }
        //获取所有key 和value
        List<Select> objs = new ArrayList<>();
        Select object = null;
        Enumeration pNames=request.getParameterNames();
        while(pNames.hasMoreElements()){
            String name=(String)pNames.nextElement();
            if ("select".equals(name)){
                continue;
            }
            if (type.indexOf("area")!=-1){
                object = new Area();
            }
            if (type.indexOf("step")!=-1){
                object = new Step();
            }
            if (type.indexOf("state")!=-1){
                object = new Astate();
            }
            if (type.indexOf("department")!=-1){
                object = new Department();
            }
            if (type.indexOf("Pstate")!=-1){
                object = new Pstate();
            }
            if (type.indexOf("type")!=-1){
                object = new Type();
            }
            int id = Integer.valueOf(name);
            String value=request.getParameter(name);
            object.setId(id);
            object.setValue(value);
            objs.add(object);
        }
        /**开始保存操作事件*/
        String userid = (String) SecurityUtils.getSubject().getPrincipal();
        Event event = new Event();
        Date date = new Date();
        event.setStartDate(date);
        event.setEndDate(date);
        event.setUname(userid);
        if (type.indexOf("area")!=-1){
            event.setEvent("修改区域下拉框");
            for (Select select : objs){
                Area area = (Area) select;
                areaDao.save(area);
            }
        }
        if (type.indexOf("step")!=-1){
            event.setEvent("修改工序下拉框");
            for (Select select : objs){
                Step area = (Step) select;
                stepDao.save(area);
            }
        }
        if ("state".equals(type)){
            event.setEvent("修改资产状态下拉框");
            for (Select select : objs){
                Astate area = (Astate) select;
                astateDao.save(area);
            }
        }
        if (type.indexOf("department")!=-1){
            event.setEvent("修改部门下拉框");
            for (Select select : objs){
                Department area = (Department) select;
                departmentDao.save(area);
            }
        }
        if (type.indexOf("type")!=-1){
            event.setEvent("修改资产类别下拉框");
            for (Select select : objs){
                Type area = (Type) select;
                typeDao.save(area);
            }
        }
        if (type.indexOf("Pstate")!=-1){
            event.setEvent("修改性能状态下拉框");
            for (Select select : objs){
                Pstate area = (Pstate) select;
                pstateDao.save(area);
            }
        }
        eventDao.save(event);

        return "ok";
    }

    @Override
    @Transactional
    public String del(String type, int id) {
        /**开始保存操作事件*/
        String userid = (String) SecurityUtils.getSubject().getPrincipal();
        Event event = new Event();
        Date date = new Date();
        event.setStartDate(date);
        event.setEndDate(date);
        event.setUname(userid);
        if (type.indexOf("area")!=-1){
            event.setEvent("删除区域下拉框内容");
            event.setDescription("删除内容为"+areaDao.findById(id).getValue());
            areaDao.deleteById(id);
            eventDao.save(event);
            return "ok";
        }
        else if (type.indexOf("step")!=-1){
            event.setEvent("删除工序下拉框内容");
            event.setDescription("删除内容为"+stepDao.findById(id).getValue());
            stepDao.deleteById(id);
            eventDao.save(event);
            return "ok";

        }
        else if ("state".equals(type)){
            event.setEvent("删除资产状态下拉框内容");
            event.setDescription("删除内容为"+astateDao.findById(id).getValue());
            astateDao.deleteById(id);
            eventDao.save(event);
            return "ok";
        }
        else if (type.indexOf("department")!=-1){
            event.setEvent("删除部门下拉框内容");
            event.setDescription("删除内容为"+departmentDao.findById(id).getValue());
            departmentDao.deleteById(id);
            eventDao.save(event);
            return "ok";
        }else if (type.indexOf("type")!=-1){
            event.setEvent("删除资产类别下拉框内容");
            event.setDescription("删除内容为"+typeDao.findById(id).getValue());
            typeDao.deleteById(id);
            eventDao.save(event);
            return "ok";
        }else if (type.indexOf("Pstate")!=-1){
            event.setEvent("删除性能状态下拉框内容");
            event.setDescription("删除内容为"+pstateDao.findById(id).getValue());
            pstateDao.deleteById(id);
            eventDao.save(event);
            return "ok";
        }
        else{
            return "fail";
        }
    }




}
