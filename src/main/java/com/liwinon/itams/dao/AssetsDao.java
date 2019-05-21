package com.liwinon.itams.dao;

import com.liwinon.itams.entity.Assets;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AssetsDao extends JpaRepository<Assets,String>, JpaSpecificationExecutor<Assets> {
    @Query(value = "select a from Assets a where a.AssetsID=:assetsID")
    Assets findByAssetsID(String assetsID);

    @Query(value = "select a from Assets a where a.DeviceID=:DeviceID")
    Assets findByDeviceID(String DeviceID);

    @Query(value = "select * from ITAMS_Assets",nativeQuery = true)
    Page<Assets> findMyAll(Pageable pageable);

    //导出搜索的
    @Query(value = "select * from ITAMS_Assets i where i.AssetsID = :content",nativeQuery = true)
    String exportSelectByAsID(String content);
    //导出全部
    @Query(value = "select * from ITAMS_Assets",nativeQuery = true)
    List<String> expotAll();
}
