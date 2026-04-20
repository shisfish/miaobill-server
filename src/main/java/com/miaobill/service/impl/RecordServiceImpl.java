package com.miaobill.service.impl;

import com.miaobill.entity.Record;
import com.miaobill.mapper.RecordMapper;
import com.miaobill.service.RecordService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RecordServiceImpl implements RecordService {

    @Resource
    private RecordMapper recordMapper;

    @Override
    public List<Record> findAll() {
        return recordMapper.findAll();
    }

    @Override
    public Record findById(Long id) {
        return recordMapper.findById(id);
    }

    @Override
    public void insert(Record record) {
        recordMapper.insert(record);
    }

    @Override
    public void update(Record record) {
        recordMapper.update(record);
    }

    @Override
    public void delete(Long id) {
        recordMapper.delete(id);
    }

    @Override
    public List<Record> findByDateRange(String startDate, String endDate) {
        // 实现日期范围查询逻辑
        return recordMapper.findAll();
    }

    @Override
    public List<Record> findByMonth(int year, int month) {
        // 实现月份查询逻辑
        return recordMapper.findAll();
    }

    @Override
    public Map<String, Object> getMonthStats(int year, int month) {
        List<Record> records = findByMonth(year, month);
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
    public Map<String, Object> getCategoryStats(int year, int month) {
        List<Record> records = findByMonth(year, month);
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
