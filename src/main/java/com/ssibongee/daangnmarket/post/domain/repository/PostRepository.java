package com.ssibongee.daangnmarket.post.domain.repository;

import com.ssibongee.daangnmarket.post.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select p from Post p where p.id = :postId and p.isRemoved = false")
    public Optional<Post> findPostById(Long postId);
}
