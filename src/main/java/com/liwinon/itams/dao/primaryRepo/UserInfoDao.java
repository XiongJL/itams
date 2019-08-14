package com.liwinon.itams.dao.primaryRepo;

import com.liwinon.itams.entity.primay.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UserInfoDao extends JpaRepository<UserInfo,Integer> , JpaSpecificationExecutor<UserInfo> {
    @Query(value = "SELECT TOP 5 * FROM ITAMS_UserInfo u " +
            "where u.UserName LIKE %:uname% or u.AssetsID LIKE %:uname% ",nativeQuery = true)
    List<UserInfo> getLikeUname(@Param("uname") String uname);

    @Query(value = "select u from  UserInfo u where u.UserID = :userid")
    List<UserInfo> findByUserID(String userid);

    @Query(value = "select u from  UserInfo u where u.DeviceID = :deviceID")
    UserInfo findByDeviceID(String deviceID);

    @Query(value = "SELECT u.UserName,u.UserID,u.UserDepartment,u.AssetsID,h.Model,u.GetTime " +
            "FROM ITAMS_UserInfo u,ITAMS_Hardware h " +
            "WHERE u.AssetsID = h.AssetsID AND h.State = 1",nativeQuery = true)
    List<String[]> export();

    //查询Assets,通过用户绑定的
    @Query(value = "select a.* from ITAMS_Assets a,ITAMS_UserInfo u where u.UserID=:content and " +
            "a.AssetsID = u.AssetsID",nativeQuery = true)
    List<String> exportSelectByUserID(String content);
}
