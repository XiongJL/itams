package com.liwinon.itams.dao.primaryRepo;

import com.liwinon.itams.entity.primay.Pstate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PstateDao extends JpaRepository<Pstate,Integer> {
    Pstate findById(int id);
}
