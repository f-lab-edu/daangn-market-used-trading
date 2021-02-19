package com.ssibongee.daangnmarket.post.service;

import com.ssibongee.daangnmarket.commons.advice.exception.CategoryNotFoundException;
import com.ssibongee.daangnmarket.post.domain.entity.Category;
import com.ssibongee.daangnmarket.post.domain.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import static com.ssibongee.daangnmarket.commons.config.CacheKeyConfig.*;

@Service
@RequiredArgsConstructor
public class TradeCategoryService implements CategoryService{

    private final CategoryRepository categoryRepository;


    @Override
    @Cacheable(key = "#categoryName", value = CATEGORY, cacheManager = "redisCacheManager", cacheNames = CATEGORY)
    public Category findCategoryByName(String categoryName) {
        return categoryRepository.findCategoryByCategoryName(categoryName)
                .orElseThrow (() -> new CategoryNotFoundException(categoryName));
    }
}
