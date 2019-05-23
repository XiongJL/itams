package com.liwinon.itams.dao;

import com.liwinon.itams.entity.RoleModel;
import com.liwinon.itams.entity.User;
import com.liwinon.itams.entity.UserRoleModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserDao extends JpaRepository<User,Integer>, JpaSpecificationExecutor<User> {
    @Query(value = "select new com.liwinon.itams.entity.UserRoleModel(u.uid,u.uname,u.pwd,r.name,r.workshop)" +
            "        from UserRole ur " +
            "        LEFT JOIN User u on u.uid= ur.uid" +
            "        LEFT JOIN Role r on ur.rid= r.rid" +
            "        where uname= :uname")
    List<UserRoleModel> findByUname(String uname);

    @Query(value = "select u.pwd from User u where uname = :uname")
    String getPwdByUname(String uname);

    //User findByUname(String name);

    @Query(value = "select p.name" +
            "                    from ITAMS_Role_Permission rp " +
            "                    LEFT JOIN ITAMS_Role r on rp.rid= r.rid " +
            "LEFT JOIN ITAMS_Permission p on rp.pid = p.pid " +
            "                   where r.name= :Role",nativeQuery = true)
    String getPermissionByRole(String Role);



}
