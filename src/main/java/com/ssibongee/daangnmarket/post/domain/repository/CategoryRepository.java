package com.ssibongee.daangnmarket.post.domain.repository;

import com.ssibongee.daangnmarket.post.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    public Optional<Category> findCategoryByCategoryName(String categoryName);
}
