package com.miaobill.mapper;

import com.miaobill.entity.Record;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface RecordMapper {
    List<Record> findAll();
    Record findById(Long id);
    void insert(Record record);
    void update(Record record);
    void delete(Long id);
}
