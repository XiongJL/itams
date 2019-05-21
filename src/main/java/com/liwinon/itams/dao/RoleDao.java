package com.liwinon.itams.dao;

import com.liwinon.itams.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleDao extends JpaRepository<Role,Integer> {

    Role findByName(String name);
}
