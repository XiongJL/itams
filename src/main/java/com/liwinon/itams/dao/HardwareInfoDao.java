package com.liwinon.itams.dao;

import com.liwinon.itams.entity.HardwareInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HardwareInfoDao extends JpaRepository<HardwareInfo,Integer>, JpaSpecificationExecutor<HardwareInfo> {
    @Query(value = "SELECT DISTINCT AssetsType FROM ITAMS_HardwareInfo ",nativeQuery = true)
    List<String> findTypes();

    @Query(value="SELECT Brand FROM ITAMS_HardwareInfo WHERE AssetsType = :type",nativeQuery = true)
    List<String> findBrandByType(String type);

    @Query(value = "SELECT Model FROM ITAMS_HardwareInfo WHERE AssetsType = :type AND Brand = :brand",nativeQuery = true)
    String findModelsByTypeAndBrand(String type,String brand);
}
