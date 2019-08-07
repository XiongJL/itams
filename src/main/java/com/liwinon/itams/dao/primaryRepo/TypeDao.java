package com.liwinon.itams.dao.primaryRepo;

import com.liwinon.itams.entity.primay.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeDao extends JpaRepository<Type,Integer> {
    Type findById(int id);
}
