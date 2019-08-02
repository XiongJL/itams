package com.liwinon.itams.dao.primaryRepo;

import com.liwinon.itams.entity.primay.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleDao extends JpaRepository<Role,Integer> {

    Role findByName(String name);
    Role findByWorkshop(String workshop);
    /**
     * 获取所有用户的所有角色,以  逗号 隔开
     * @param pageable
     * @return
     */
    @Query(value = "SELECT t1.uid,t1.uname,t1.PERSONID,STUFF((SELECT ',' + workshop FROM view_roles " +
            "WHERE t1.uid=uid " +
            "FOR XML PATH('')),1, 1, '') AS roles " +
            "FROM view_roles as t1 " +
            "GROUP BY t1.uid,t1.uname,t1.PERSONID",nativeQuery = true)
    Page<String[]> getAllUserRole(Pageable pageable);

    /**
     * 查询用户的所有角色,以  逗号 隔开
     * @param pageable
     * @return
     */
    @Query(value = "SELECT t1.uid,t1.uname,t1.PERSONID,STUFF((SELECT ',' + workshop FROM view_roles " +
            "WHERE t1.uid=uid " +
            "FOR XML PATH('')),1, 1, '') AS roles " +
            "FROM view_roles as t1 " +
            "where concat(t1.uname,t1.PERSONID) like %:content% " +
            "GROUP BY t1.uid,t1.uname,t1.PERSONID ",nativeQuery = true)
    Page<String[]> getSearchUserRole(String content, Pageable pageable);


    @Query(value = "SELECT DISTINCT STUFF((SELECT ',' + workshop FROM view_roles " +
            "WHERE t1.uid=uid " +
            "FOR XML PATH('')),1, 1, '') AS roles " +
            "FROM view_roles as t1 " +
            "where PERSONID = :userid ",nativeQuery = true)
    String getMyRole(String userid);


    @Query(value = "SELECT DISTINCT workshop FROM ITAMS_Role",nativeQuery = true)
    List<String> findWorkshops();

    Role findByRid(int rid);
}
