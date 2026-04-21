package com.miaobill.controller;

import com.miaobill.context.UserContextHolder;
import com.miaobill.entity.Category;
import com.miaobill.service.CategoryService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @GetMapping("/type/{type}")
    public List<Category> findByType(@PathVariable String type) {
        Long userId = UserContextHolder.getCurrentUserId();
        return categoryService.getCategoriesByType(type, userId);
    }

    @PostMapping
    public void addCategory(@RequestBody Category category) {
        Long userId = UserContextHolder.getCurrentUserId();
        categoryService.addCategory(category, userId);
    }

    @PutMapping("/{id}")
    public void updateCategory(@PathVariable Long id,
                               @RequestBody Category category) {
        category.setId(id);
        Long userId = UserContextHolder.getCurrentUserId();
        categoryService.updateCategory(category, userId);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        Long userId = UserContextHolder.getCurrentUserId();
        categoryService.deleteCategory(id, userId);
    }
}
