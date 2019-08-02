package com.liwinon.itams.service;

import com.liwinon.itams.entity.primay.Area;
import com.liwinon.itams.entity.primay.Astate;
import com.liwinon.itams.entity.primay.Department;
import com.liwinon.itams.entity.primay.Step;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface selectService {
    List<Astate> getAllAstate();

    List<Area> getAllArea();

    List<Department> getAllDepartment();

    List<Step> getAllStep();

    String changeValues(HttpServletRequest request);

    String del(String type, int id);
}
