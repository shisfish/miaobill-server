package com.miaobill.mapper;

import com.miaobill.entity.Record;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface RecordMapper {
    List<Record> findByUserId(@Param("userId") Long userId);
    Record findById(@Param("id") Long id, @Param("userId") Long userId);
    void insert(Record record);
    void update(Record record);
    void delete(@Param("id") Long id, @Param("userId") Long userId);
    List<Record> findByDateRange(@Param("userId") Long userId, @Param("startDate") String startDate, @Param("endDate") String endDate);
    List<Record> findByMonth(@Param("userId") Long userId, @Param("year") int year, @Param("month") int month);
}
