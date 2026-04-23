package com.miaobill.controller;

import com.miaobill.common.ApiResponse;
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
    public ApiResponse<List<Record>> findAll() {
        Long userId = UserContextHolder.getCurrentUserId();
        return ApiResponse.success(recordService.findAll(userId));
    }

    @GetMapping("/{id}")
    public ApiResponse<Record> findById(@PathVariable Long id) {
        Long userId = UserContextHolder.getCurrentUserId();
        return ApiResponse.success(recordService.findById(id, userId));
    }

    @PostMapping
    public ApiResponse<Void> insert(@RequestBody Record record) {
        Long userId = UserContextHolder.getCurrentUserId();
        recordService.insert(record, userId);
        return ApiResponse.success(null);
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> update(@PathVariable Long id, @RequestBody Record record) {
        record.setId(id);
        Long userId = UserContextHolder.getCurrentUserId();
        recordService.update(record, userId);
        return ApiResponse.success(null);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        Long userId = UserContextHolder.getCurrentUserId();
        recordService.delete(id, userId);
        return ApiResponse.success(null);
    }

    @GetMapping("/range")
    public ApiResponse<List<Record>> findByDateRange(@RequestParam String startDate,
                                        @RequestParam String endDate) {
        Long userId = UserContextHolder.getCurrentUserId();
        return ApiResponse.success(recordService.findByDateRange(userId, startDate, endDate));
    }

    @GetMapping("/month/{year}/{month}")
    public ApiResponse<List<Record>> findByMonth(@PathVariable int year,
                                    @PathVariable int month) {
        Long userId = UserContextHolder.getCurrentUserId();
        return ApiResponse.success(recordService.findByMonth(userId, year, month));
    }

    @GetMapping("/stats/month/{year}/{month}")
    public ApiResponse<Map<String, Object>> getMonthStats(@PathVariable int year,
                                             @PathVariable int month) {
        Long userId = UserContextHolder.getCurrentUserId();
        return ApiResponse.success(recordService.getMonthStats(userId, year, month));
    }

    @GetMapping("/stats/category/{year}/{month}")
    public ApiResponse<Map<String, Object>> getCategoryStats(@PathVariable int year,
                                                @PathVariable int month) {
        Long userId = UserContextHolder.getCurrentUserId();
        return ApiResponse.success(recordService.getCategoryStats(userId, year, month));
    }
}
