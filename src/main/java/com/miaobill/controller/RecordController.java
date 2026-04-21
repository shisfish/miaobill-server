package com.miaobill.controller;

import com.miaobill.context.UserContextHolder;
import com.miaobill.entity.Record;
import com.miaobill.service.RecordService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/records")
public class RecordController {

    @Resource
    private RecordService recordService;

    @GetMapping
    public List<Record> findAll() {
        Long userId = UserContextHolder.getCurrentUserId();
        return recordService.findAll(userId);
    }

    @GetMapping("/{id}")
    public Record findById(@PathVariable Long id) {
        Long userId = UserContextHolder.getCurrentUserId();
        return recordService.findById(id, userId);
    }

    @PostMapping
    public void insert(@RequestBody Record record) {
        Long userId = UserContextHolder.getCurrentUserId();
        recordService.insert(record, userId);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody Record record) {
        record.setId(id);
        Long userId = UserContextHolder.getCurrentUserId();
        recordService.update(record, userId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Long userId = UserContextHolder.getCurrentUserId();
        recordService.delete(id, userId);
    }

    @GetMapping("/range")
    public List<Record> findByDateRange(@RequestParam String startDate,
                                        @RequestParam String endDate) {
        Long userId = UserContextHolder.getCurrentUserId();
        return recordService.findByDateRange(userId, startDate, endDate);
    }

    @GetMapping("/month/{year}/{month}")
    public List<Record> findByMonth(@PathVariable int year,
                                    @PathVariable int month) {
        Long userId = UserContextHolder.getCurrentUserId();
        return recordService.findByMonth(userId, year, month);
    }

    @GetMapping("/stats/month/{year}/{month}")
    public Map<String, Object> getMonthStats(@PathVariable int year,
                                             @PathVariable int month) {
        Long userId = UserContextHolder.getCurrentUserId();
        return recordService.getMonthStats(userId, year, month);
    }

    @GetMapping("/stats/category/{year}/{month}")
    public Map<String, Object> getCategoryStats(@PathVariable int year,
                                                @PathVariable int month) {
        Long userId = UserContextHolder.getCurrentUserId();
        return recordService.getCategoryStats(userId, year, month);
    }
}
