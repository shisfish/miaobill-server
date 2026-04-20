package com.miaobill.controller;

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
        return recordService.findAll();
    }

    @GetMapping("/{id}")
    public Record findById(@PathVariable Long id) {
        return recordService.findById(id);
    }

    @PostMapping
    public void insert(@RequestBody Record record) {
        recordService.insert(record);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody Record record) {
        record.setId(id);
        recordService.update(record);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        recordService.delete(id);
    }

    @GetMapping("/range")
    public List<Record> findByDateRange(@RequestParam String startDate, @RequestParam String endDate) {
        return recordService.findByDateRange(startDate, endDate);
    }

    @GetMapping("/month/{year}/{month}")
    public List<Record> findByMonth(@PathVariable int year, @PathVariable int month) {
        return recordService.findByMonth(year, month);
    }

    @GetMapping("/stats/month/{year}/{month}")
    public Map<String, Object> getMonthStats(@PathVariable int year, @PathVariable int month) {
        return recordService.getMonthStats(year, month);
    }

    @GetMapping("/stats/category/{year}/{month}")
    public Map<String, Object> getCategoryStats(@PathVariable int year, @PathVariable int month) {
        return recordService.getCategoryStats(year, month);
    }
}
