package com.liwinon.itams.dao.primaryRepo;

import com.liwinon.itams.entity.primay.Astate;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AstateDao extends JpaRepository<Astate,Integer> {

    Astate findById(int id);

}
