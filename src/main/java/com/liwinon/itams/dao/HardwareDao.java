package com.liwinon.itams.dao;

import com.liwinon.itams.entity.Hardware;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HardwareDao extends JpaRepository<Hardware,Integer> {
    @Query(value = "select h from Hardware h where h.AssetsID=:assetsID")
    Hardware findByAssetsID(String assetsID);

    @Query(value = "SELECT TOP 5 * FROM ITAMS_Hardware u " +
            "where u.AssetsID LIKE %:uname% ",nativeQuery = true)
    List<Hardware> getLikeUname(String uname);

    @Query(value="SELECT COUNT(State) FROM ITAMS_Hardware where State = 1",
            nativeQuery = true)
    long countUse();

    @Query(value = "SELECT h FROM Hardware h")
    Page<Hardware> findMyAll(Pageable pageable);
}
