package com.liwinon.itams.dao;

import com.liwinon.itams.entity.Sap_Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SapDao extends JpaRepository<Sap_Users,String> {
    @Query(value = "select s.NAME from Sap_Users s where s.PERSONID = :PERSONID")
    String findNameByUserId(String PERSONID);
}
