package com.ssibongee.daangnmarket.image.domain.repository;

import com.ssibongee.daangnmarket.image.domain.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
