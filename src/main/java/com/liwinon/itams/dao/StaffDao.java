package com.liwinon.itams.dao;

import com.liwinon.itams.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StaffDao extends JpaRepository<Staff,Integer>, JpaSpecificationExecutor<Staff> {

    @Query(value = " SELECT Staff FROM ITAMS_Staff",nativeQuery = true)
    List<String> findAllStaff();
}
