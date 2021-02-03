package com.ssibongee.daangnmarket.service.post;

import com.ssibongee.daangnmarket.advice.exception.CategoryNotFoundException;
import com.ssibongee.daangnmarket.domain.entity.Category;
import com.ssibongee.daangnmarket.domain.repository.post.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TradeCategoryService implements CategoryService{

    private final CategoryRepository categoryRepository;


    @Override
    @Cacheable(key = "#categoryName", value = "category")
    public Category findCategoryByName(String categoryName) {
        return categoryRepository.findCategoryByCategoryName(categoryName)
                .orElseThrow (() -> new CategoryNotFoundException(categoryName));
    }
}
