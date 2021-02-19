package com.ssibongee.daangnmarket.post.service;

import com.ssibongee.daangnmarket.post.domain.entity.Category;

public interface CategoryService {

    public Category findCategoryByName(String categoryName);
}
