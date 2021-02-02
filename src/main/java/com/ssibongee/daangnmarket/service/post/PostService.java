package com.ssibongee.daangnmarket.service.post;

import com.ssibongee.daangnmarket.domain.dto.PostRequest;
import com.ssibongee.daangnmarket.domain.entity.Member;
import com.ssibongee.daangnmarket.domain.entity.Post;

public interface PostService {

    public void createNewPost(PostRequest postRequest, Member member);

    public Post findPostById(Long postId);

    public void updatePost(Post post, PostRequest postRequest);
}
