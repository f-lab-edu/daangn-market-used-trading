package com.ssibongee.daangnmarket.domain.repository.post;

import com.ssibongee.daangnmarket.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    public Optional<Post> findPostById(Long postId);
}
