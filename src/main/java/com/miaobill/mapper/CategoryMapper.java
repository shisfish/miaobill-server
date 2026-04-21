package com.miaobill.mapper;

import com.miaobill.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface CategoryMapper {
    List<Category> findByType(@Param("type") String type, @Param("userId") Long userId);
    List<Category> findDefaultByType(@Param("type") String type);
    void insert(Category category);
    void delete(@Param("id") Long id, @Param("userId") Long userId);
    void update(@Param("category") Category category, @Param("userId") Long userId);
    int countByUserId(@Param("userId") Long userId);
}
