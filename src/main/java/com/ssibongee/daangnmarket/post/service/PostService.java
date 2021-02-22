package com.ssibongee.daangnmarket.post.service;

import com.ssibongee.daangnmarket.post.dto.PostRequest;
import com.ssibongee.daangnmarket.member.domain.entity.Member;
import com.ssibongee.daangnmarket.post.domain.entity.Post;

public interface PostService {

    public void createNewPost(PostRequest postRequest, Member member);

    public Post findPostById(Long postId);

    public void updatePost(Post post, PostRequest postRequest);

    public void removePost(Post post);

    public boolean isMatchedAuthor(Post post);
}
