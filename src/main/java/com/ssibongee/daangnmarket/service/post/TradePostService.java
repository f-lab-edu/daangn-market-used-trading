package com.ssibongee.daangnmarket.service.post;

import com.ssibongee.daangnmarket.advice.exception.PostNotFoundException;
import com.ssibongee.daangnmarket.commons.annotation.AreaInfoRequired;
import com.ssibongee.daangnmarket.domain.dto.PostRequest;
import com.ssibongee.daangnmarket.domain.entity.Category;
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
    private final CategoryService categoryService;


    @Override
    @AreaInfoRequired
    @Transactional
    public void createNewPost(PostRequest postRequest, Member member) {

        Post post = postRequest.toEntity(member);
        Category category = categoryService.findCategoryByName(postRequest.getCategory());

        post.setCategory(category);

        postRepository.save(post);
    }

    @Override
    public Post findPostById(Long postId) {
        return postRepository.findPostById(postId).orElseThrow(PostNotFoundException::new);
    }

    @Override
    @Transactional
    public void updatePost(Post post, PostRequest postRequest) {

        Category category = categoryService.findCategoryByName(postRequest.getCategory());

        post.updatePost(postRequest);
        post.setCategory(category);
    }

}
