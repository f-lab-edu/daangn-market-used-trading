package com.ssibongee.daangnmarket.domain.repository.post;

import com.ssibongee.daangnmarket.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    public Optional<Category> findCategoryByCategoryName(String categoryName);
}
