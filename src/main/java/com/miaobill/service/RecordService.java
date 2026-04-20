package com.miaobill.service;

import com.miaobill.entity.Record;
import java.util.List;
import java.util.Map;

public interface RecordService {
    List<Record> findAll();
    Record findById(Long id);
    void insert(Record record);
    void update(Record record);
    void delete(Long id);
    List<Record> findByDateRange(String startDate, String endDate);
    List<Record> findByMonth(int year, int month);
    Map<String, Object> getMonthStats(int year, int month);
    Map<String, Object> getCategoryStats(int year, int month);
}
