package com.miaobill.service;

import com.miaobill.entity.Category;
import java.util.List;

public interface CategoryService {
    List<Category> getCategoriesByType(String type, Long userId);
    void addCategory(Category category, Long userId);
    void deleteCategory(Long id, Long userId);
    void updateCategory(Category category, Long userId);
}
