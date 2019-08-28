package com.liwinon.itams.dao.primaryRepo;

import com.liwinon.itams.entity.primay.Assets;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface AssetsDao extends JpaRepository<Assets,String>, JpaSpecificationExecutor<Assets> {
    @Query(value = "select a from Assets a where a.AssetsID=:assetsID")
    Assets findByAssetsID(String assetsID);

    @Query(value = "select a from Assets a where a.DeviceID=:DeviceID")
    Assets findByDeviceID(String DeviceID);

    @Query(value = "select * from ITAMS_Assets",nativeQuery = true)
//    @Query(value = "Select *" +
//            "from " +
//            "(" +
//            "    select " +
//            "    ROW_NUMBER() over(order by DeviceID asc)  as __tempId," +
//            "    * From (select t.*  from ITAMS_Assets t)as a" +
//            ")as a" +
//            " Where a.__tempId between 1 And 10",
//            countQuery = "SELECT count(*) FROM ITAMS_Assets",
//            nativeQuery = true)
    Page<Assets> findMyAll(Pageable pageable);

    //导出搜索的资产id
    @Query(value = "select * from ITAMS_Assets i where i.AssetsID = :content",nativeQuery = true)
    String exportSelectByAsID(String content);
    //导出搜索的设备id
    @Query(value = "select * from ITAMS_Assets i where i.DeviceID = :content",nativeQuery = true)
    String exportByDeviceID(String content);
    //导出搜索的日期
    @Query(value = "select * from ITAMS_Assets i where i.EntryDate between  :date1 and :date2",nativeQuery = true)
    List<String> exportByDate(Date date1, Date date2);
    //导出详细位置搜索的结果
    @Query(value = "select * from ITAMS_Assets  where Location Like %:l1% or Location Like %:l2%" +
            " or Location Like %:l3% or Location Like %:l4% or Location Like %:l5%",nativeQuery = true)
    List<String> exportByLocation(String l1, String l2, String l3, String l4, String l5);

    //导出资产类别搜索的结果
    @Query(value = "select * from ITAMS_Assets  where AssetsCategory Like %:l1% or AssetsCategory Like %:l2%" +
            " or AssetsCategory Like %:l3% or AssetsCategory Like %:l4% or AssetsCategory Like %:l5%",nativeQuery = true)
    List<String> exportCategory(String l1, String l2, String l3, String l4, String l5);
    //导出责任人搜索的结果
    @Query(value = "select * from ITAMS_Assets  where Personliable Like %:l1% or Personliable Like %:l2%" +
            " or Personliable Like %:l3% or Personliable Like %:l4% or Personliable Like %:l5%",nativeQuery = true)
    List<String> exportPerson(String l1, String l2, String l3, String l4, String l5);

    //导出资产状态搜索的结果
    @Query(value = "select * from ITAMS_Assets  where AState = :l1 or AState = :l2 or AState = :l3 or AState = :l4",nativeQuery = true)
    List<String> exportByAState(String l1, String l2, String l3, String l4);

    //导出全部
    @Query(value = "select * from ITAMS_Assets",nativeQuery = true)
    List<String> expotAll();

    //根据日期查询
    @Query(value = "select * from ITAMS_Assets i where i.EntryDate between  :date1 and :date2", nativeQuery = true)
    Page<Assets> findBetweenDate(Date date1, Date date2, Pageable pageable);

    //根据位置模糊查询 , 最多搜索5个位置
    @Query(value = "select * from ITAMS_Assets  where Location Like %:l1% or Location Like %:l2%" +
            " or Location Like %:l3% or Location Like %:l4% or Location Like %:l5%",nativeQuery = true)
    Page<Assets> findByLocation(String l1,String l2,String l3,String l4,String l5,Pageable pageable);

    //根据资产类别搜索, 最多五个种类
    @Query(value = "select * from ITAMS_Assets  where AssetsCategory Like %:l1% or AssetsCategory Like %:l2%" +
            " or AssetsCategory Like %:l3% or AssetsCategory Like %:l4% or AssetsCategory Like %:l5%",nativeQuery = true)
    Page<Assets> findByCategory(String l1, String l2, String l3, String l4, String l5, Pageable pageable);
    //根据责任人搜索, 最多五位
    @Query(value = "select * from ITAMS_Assets  where Personliable Like %:l1% or Personliable Like %:l2%" +
            " or Personliable Like %:l3% or Personliable Like %:l4% or Personliable Like %:l5%",nativeQuery = true)
    Page<Assets> findByPerson(String l1, String l2, String l3, String l4, String l5, Pageable pageable);
    //根据资产状态查询
    @Query(value = "select * from ITAMS_Assets  where AState = :l1 or AState = :l2 or AState = :l3 or AState = :l4",nativeQuery = true)
    Page<Assets> findBtAState(String l1, String l2, String l3, String l4, Pageable pageable);

    //根据资产名模糊搜索
    @Query(value="select * from ITAMS_Assets where AssetsName LIKE %:l1% OR AssetsName like %:l2%" +
            " OR AssetsName like %:l3% OR AssetsName like %:l4% OR AssetsName like %:l5%",nativeQuery = true)
    Page<Assets> findByAssetsName(String l1, String l2, String l3, String l4, String l5, Pageable pageable);
}
