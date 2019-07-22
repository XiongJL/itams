package com.liwinon.itams.service;

import java.util.List;

public interface searchService {

   // public List<String[]> lianxiang(String uname);

    //public Object[] search(String input);

    //吧搜索结果写入Excel
    public List<String[]> getdata(String content , String type);
}
