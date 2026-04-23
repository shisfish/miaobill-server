package com.miaobill.mapper;

import com.miaobill.entity.CheckinRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.time.LocalDate;
import java.util.List;

@Mapper
public interface CheckinRecordMapper {
    CheckinRecord findByUserIdAndDate(@Param("userId") Long userId, @Param("checkinDate") LocalDate checkinDate);
    List<CheckinRecord> findByUserId(@Param("userId") Long userId);
    CheckinRecord findLatestByUserId(@Param("userId") Long userId);
    void insert(CheckinRecord record);
    int countByUserId(@Param("userId") Long userId);
}
