package com.liwinon.itams.dao.primaryRepo;

import com.liwinon.itams.entity.primay.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AreaDao extends JpaRepository<Area,Integer> {

    Area findById(int id);
    @Query(value = "select a.value from Area a")
    List<String> findAllArea();
}
