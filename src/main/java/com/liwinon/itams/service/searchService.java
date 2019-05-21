package com.liwinon.itams.service;

import com.liwinon.itams.entity.Hardware;

import java.util.List;

public interface searchService {

    public List<String[]> lianxiang(String uname);

    public Object[] search(String input);


}
