package com.ssibongee.daangnmarket.post.domain.repository;

import com.ssibongee.daangnmarket.post.domain.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostSearchRepository extends JpaRepository<Post, Long> {

    @Query(value = "SELECT post FROM Post post JOIN FETCH post.author WHERE post.address.state=:state AND post.address.city=:city AND post.address.town=:town",
    countQuery = "SELECT COUNT(*) FROM Post post WHERE post.address.state=:state AND post.address.city=:city AND post.address.town=:town")
    public Page<Post> findAllByMemberAddress(String state, String city, String town, Pageable pageable);
}
