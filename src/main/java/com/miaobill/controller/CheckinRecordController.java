package com.miaobill.controller;

import com.miaobill.common.ApiResponse;
import com.miaobill.context.UserContextHolder;
import com.miaobill.entity.CheckinRecord;
import com.miaobill.service.CheckinRecordService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/api/checkin")
public class CheckinRecordController {

    @Resource
    private CheckinRecordService checkinRecordService;

    @PostMapping
    public ApiResponse<CheckinRecord> checkin() {
        Long userId = UserContextHolder.getCurrentUserId();
        return ApiResponse.success(checkinRecordService.checkin(userId));
    }

    @GetMapping("/stats")
    public ApiResponse<Map<String, Object>> getCheckinStats() {
        Long userId = UserContextHolder.getCurrentUserId();
        return ApiResponse.success(checkinRecordService.getCheckinStats(userId));
    }

    @GetMapping("/today")
    public ApiResponse<Map<String, Object>> hasCheckedToday() {
        Long userId = UserContextHolder.getCurrentUserId();
        boolean checked = checkinRecordService.hasCheckedToday(userId);
        Map<String, Object> result = new java.util.HashMap<>();
        result.put("checked", checked);
        return ApiResponse.success(result);
    }
}
