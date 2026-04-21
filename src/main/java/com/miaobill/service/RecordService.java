package com.miaobill.service;

import com.miaobill.entity.Record;
import java.util.List;
import java.util.Map;

public interface RecordService {
    List<Record> findAll(Long userId);
    Record findById(Long id, Long userId);
    void insert(Record record, Long userId);
    void update(Record record, Long userId);
    void delete(Long id, Long userId);
    List<Record> findByDateRange(Long userId, String startDate, String endDate);
    List<Record> findByMonth(Long userId, int year, int month);
    Map<String, Object> getMonthStats(Long userId, int year, int month);
    Map<String, Object> getCategoryStats(Long userId, int year, int month);
}
