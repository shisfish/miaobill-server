package com.miaobill.service.impl;

import com.miaobill.entity.Record;
import com.miaobill.mapper.RecordMapper;
import com.miaobill.service.RecordService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RecordServiceImpl implements RecordService {

    @Resource
    private RecordMapper recordMapper;

    @Override
    public List<Record> findAll(Long userId) {
        return recordMapper.findByUserId(userId);
    }

    @Override
    public Record findById(Long id, Long userId) {
        return recordMapper.findById(id, userId);
    }

    @Override
    public void insert(Record record, Long userId) {
        record.setUserId(userId);
        record.setCreateTime(LocalDateTime.now());
        recordMapper.insert(record);
    }

    @Override
    public void update(Record record, Long userId) {
        record.setUserId(userId);
        recordMapper.update(record);
    }

    @Override
    public void delete(Long id, Long userId) {
        recordMapper.delete(id, userId);
    }

    @Override
    public List<Record> findByDateRange(Long userId, String startDate, String endDate) {
        return recordMapper.findByDateRange(userId, startDate, endDate);
    }

    @Override
    public List<Record> findByMonth(Long userId, int year, int month) {
        return recordMapper.findByMonth(userId, year, month);
    }

    @Override
    public Map<String, Object> getMonthStats(Long userId, int year, int month) {
        List<Record> records = findByMonth(userId, year, month);
        BigDecimal totalExpense = BigDecimal.ZERO;
        BigDecimal totalIncome = BigDecimal.ZERO;

        for (Record record : records) {
            if ("expense".equals(record.getType())) {
                totalExpense = totalExpense.add(record.getAmount());
            } else if ("income".equals(record.getType())) {
                totalIncome = totalIncome.add(record.getAmount());
            }
        }

        Map<String, Object> stats = new HashMap<>();
        stats.put("totalExpense", totalExpense);
        stats.put("totalIncome", totalIncome);
        stats.put("recordsCount", records.size());

        return stats;
    }

    @Override
    public Map<String, Object> getCategoryStats(Long userId, int year, int month) {
        List<Record> records = findByMonth(userId, year, month);
        Map<String, BigDecimal> categoryMap = new HashMap<>();
        BigDecimal totalExpense = BigDecimal.ZERO;

        for (Record record : records) {
            if ("expense".equals(record.getType())) {
                BigDecimal amount = record.getAmount();
                totalExpense = totalExpense.add(amount);
                categoryMap.put(record.getCategory(), categoryMap.getOrDefault(record.getCategory(), BigDecimal.ZERO).add(amount));
            }
        }

        Map<String, Object> stats = new HashMap<>();
        stats.put("categoryMap", categoryMap);
        stats.put("totalExpense", totalExpense);

        return stats;
    }
}
