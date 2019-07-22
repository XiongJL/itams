package com.liwinon.itams.dao.primaryRepo;

import com.liwinon.itams.entity.primay.Apply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ApplyDao extends JpaRepository<Apply,Integer>, JpaSpecificationExecutor<Apply> {
    List<Apply> findByUid(int uid);
}
