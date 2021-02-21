package com.ssibongee.daangnmarket.post.domain.repository;

import com.ssibongee.daangnmarket.post.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE p.id = :postId AND p.removed = false")
    public Optional<Post> findPostById(Long postId);
}
