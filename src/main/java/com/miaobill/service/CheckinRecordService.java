package com.miaobill.service;

import com.miaobill.entity.CheckinRecord;
import java.util.Map;

public interface CheckinRecordService {
    CheckinRecord checkin(Long userId);
    Map<String, Object> getCheckinStats(Long userId);
    boolean hasCheckedToday(Long userId);
}
