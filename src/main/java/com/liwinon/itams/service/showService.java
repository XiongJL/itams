package com.liwinon.itams.service;

import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface showService {
     Map<String ,Object> tablesData(Pageable pageable);

     Map<String ,Object>  searchData(String content,String type,Pageable pageable);
}
