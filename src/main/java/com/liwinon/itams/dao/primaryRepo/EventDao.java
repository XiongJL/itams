package com.liwinon.itams.dao.primaryRepo;

import com.liwinon.itams.entity.primay.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventDao extends JpaRepository<Event,String> {
    @Query(value = "select top 1 * from ITAMS_Event where FormID=:Formid and state='流程中'  ORDER BY StartDate DESC",nativeQuery = true)
    Event findByFormID(String Formid);
    //查询此设备是否在流程中,或已报废,已转售,报废改造,流程终止
    @Query(value = "select * from ITAMS_Event where DeviceID =:deviceid and " +
            "(State='流程中' or State='已报废' or State='已转售'or State='报废改造')",nativeQuery = true)
    List<Event> findByDeviceIDAndState(String deviceid);
}
