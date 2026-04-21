package com.miaobill.service.impl;

import com.miaobill.entity.Category;
import com.miaobill.mapper.CategoryMapper;
import com.miaobill.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> getCategoriesByType(String type, Long userId) {
        List<Category> categories = categoryMapper.findByType(type, userId);
        if (categories.isEmpty()) {
            categories = initDefaultCategories(type, userId);
        }
        return categories;
    }

    @Transactional
    public List<Category> initDefaultCategories(String type, Long userId) {
        List<Category> defaults = categoryMapper.findDefaultByType(type);
        List<Category> categories = new ArrayList<>();

        for (int i = 0; i < defaults.size(); i++) {
            Category def = defaults.get(i);
            Category category = new Category();
            category.setName(def.getName());
            category.setIcon(def.getIcon());
            category.setType(def.getType());
            category.setSortOrder(def.getSortOrder() != null ? def.getSortOrder() : i + 1);
            category.setIsDefault(0);
            category.setUserId(userId);
            category.setCreateTime(LocalDateTime.now());
            categoryMapper.insert(category);
            categories.add(category);
        }
        return categories;
    }

    @Override
    public void addCategory(Category category, Long userId) {
        category.setUserId(userId);
        category.setIsDefault(0);
        category.setCreateTime(LocalDateTime.now());
        if (category.getSortOrder() == null) {
            List<Category> existing = categoryMapper.findByType(category.getType(), userId);
            category.setSortOrder(existing.size() + 1);
        }
        categoryMapper.insert(category);
    }

    @Override
    public void deleteCategory(Long id, Long userId) {
        categoryMapper.delete(id, userId);
    }

    @Override
    public void updateCategory(Category category, Long userId) {
        categoryMapper.update(category, userId);
    }
}
