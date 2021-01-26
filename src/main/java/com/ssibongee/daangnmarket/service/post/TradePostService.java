package com.ssibongee.daangnmarket.service.post;

import com.ssibongee.daangnmarket.domain.dto.PostCreateRequest;
import com.ssibongee.daangnmarket.domain.entity.Member;
import com.ssibongee.daangnmarket.domain.entity.Post;
import com.ssibongee.daangnmarket.domain.repository.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TradePostService implements PostService {

    private final PostRepository postRepository;

    @Override
    @Transactional
    public void createNewPost(PostCreateRequest postCreateRequest, Member member) {
        Post post = postCreateRequest.toEntity(member);
        postRepository.save(post);
    }
}
