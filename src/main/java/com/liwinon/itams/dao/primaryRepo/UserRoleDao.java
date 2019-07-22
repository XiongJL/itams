package com.liwinon.itams.dao.primaryRepo;

import com.liwinon.itams.entity.primay.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface UserRoleDao extends JpaRepository<UserRole,Integer>, JpaSpecificationExecutor<UserRole> {

    List<UserRole> findByUid(int uid);
}
