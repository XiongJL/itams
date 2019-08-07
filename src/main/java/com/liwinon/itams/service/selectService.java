package com.liwinon.itams.service;

import com.liwinon.itams.entity.primay.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface selectService {
    List<Astate> getAllAstate();

    List<Pstate> getAllPstate();

    List<Area> getAllArea();

    List<Department> getAllDepartment();

    List<Step> getAllStep();

    String changeValues(HttpServletRequest request);

    String del(String type, int id);

    List<Type> getAllType();
}
