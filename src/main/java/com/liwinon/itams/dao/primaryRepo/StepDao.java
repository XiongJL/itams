package com.liwinon.itams.dao.primaryRepo;

import com.liwinon.itams.entity.primay.Step;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StepDao extends JpaRepository<Step,Integer> {

    Step findById(int id);
}
