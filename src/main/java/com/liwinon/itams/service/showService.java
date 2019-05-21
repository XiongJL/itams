package com.liwinon.itams.service;

import com.liwinon.itams.entity.DatasShowModel;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface showService {
     Map<String ,Object> tablesData(Pageable pageable);

     List<DatasShowModel>  searchData(String content,String type);
}
