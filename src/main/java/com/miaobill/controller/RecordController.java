package com.miaobill.controller;

import com.miaobill.entity.Record;
import com.miaobill.service.RecordService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

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
}
