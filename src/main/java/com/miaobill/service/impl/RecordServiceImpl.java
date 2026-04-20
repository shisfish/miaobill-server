package com.miaobill.service.impl;

import com.miaobill.entity.Record;
import com.miaobill.mapper.RecordMapper;
import com.miaobill.service.RecordService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

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
}
