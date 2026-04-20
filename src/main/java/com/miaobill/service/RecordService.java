package com.miaobill.service;

import com.miaobill.entity.Record;
import java.util.List;

public interface RecordService {
    List<Record> findAll();
    Record findById(Long id);
    void insert(Record record);
    void update(Record record);
    void delete(Long id);
}
