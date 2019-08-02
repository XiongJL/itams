package com.liwinon.itams.dao.primaryRepo;

import com.liwinon.itams.entity.primay.Department;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DepartmentDao extends JpaRepository<Department,Integer> {
    Department findById(int id);
}
