package com.ssibongee.daangnmarket.service.post;

import com.ssibongee.daangnmarket.domain.dto.PostCreateRequest;
import com.ssibongee.daangnmarket.domain.entity.Member;

public interface PostService {

    public void createNewPost(PostCreateRequest postCreateRequest, Member member);
}
