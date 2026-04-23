package com.miaobill.controller;

import com.miaobill.common.ApiResponse;
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
    public ApiResponse<List<Category>> findByType(@PathVariable String type) {
        Long userId = UserContextHolder.getCurrentUserId();
        return ApiResponse.success(categoryService.getCategoriesByType(type, userId));
    }

    @PostMapping
    public ApiResponse<Void> addCategory(@RequestBody Category category) {
        Long userId = UserContextHolder.getCurrentUserId();
        categoryService.addCategory(category, userId);
        return ApiResponse.success(null);
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> updateCategory(@PathVariable Long id,
                               @RequestBody Category category) {
        category.setId(id);
        Long userId = UserContextHolder.getCurrentUserId();
        categoryService.updateCategory(category, userId);
        return ApiResponse.success(null);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteCategory(@PathVariable Long id) {
        Long userId = UserContextHolder.getCurrentUserId();
        categoryService.deleteCategory(id, userId);
        return ApiResponse.success(null);
    }
}
