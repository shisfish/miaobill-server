package com.miaobill.service.impl;

import com.miaobill.entity.CheckinRecord;
import com.miaobill.mapper.CheckinRecordMapper;
import com.miaobill.service.CheckinRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CheckinRecordServiceImpl implements CheckinRecordService {

    @Resource
    private CheckinRecordMapper checkinRecordMapper;

    @Override
    @Transactional
    public CheckinRecord checkin(Long userId) {
        LocalDate today = LocalDate.now();
        CheckinRecord existing = checkinRecordMapper.findByUserIdAndDate(userId, today);
        if (existing != null) {
            return existing;
        }

        CheckinRecord latest = checkinRecordMapper.findLatestByUserId(userId);
        int continuousDays = 1;

        if (latest != null) {
            LocalDate latestDate = latest.getCheckinDate();
            long daysBetween = ChronoUnit.DAYS.between(latestDate, today);
            if (daysBetween == 1) {
                continuousDays = latest.getContinuousDays() + 1;
            }
        }

        CheckinRecord record = new CheckinRecord();
        record.setUserId(userId);
        record.setCheckinDate(today);
        record.setContinuousDays(continuousDays);
        record.setCreateTime(LocalDateTime.now());
        checkinRecordMapper.insert(record);
        return record;
    }

    @Override
    public Map<String, Object> getCheckinStats(Long userId) {
        int totalDays = checkinRecordMapper.countByUserId(userId);
        CheckinRecord latest = checkinRecordMapper.findLatestByUserId(userId);
        int continuousDays = latest != null ? latest.getContinuousDays() : 0;

        Map<String, Object> stats = new HashMap<>();
        stats.put("totalDays", totalDays);
        stats.put("continuousDays", continuousDays);
        stats.put("latestCheckinDate", latest != null ? latest.getCheckinDate() : null);
        return stats;
    }

    @Override
    public boolean hasCheckedToday(Long userId) {
        LocalDate today = LocalDate.now();
        return checkinRecordMapper.findByUserIdAndDate(userId, today) != null;
    }
}
