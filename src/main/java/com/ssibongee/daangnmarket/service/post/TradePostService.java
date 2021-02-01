package com.ssibongee.daangnmarket.service.post;

import com.ssibongee.daangnmarket.advice.exception.CategoryNotFoundException;
import com.ssibongee.daangnmarket.domain.dto.PostCreateRequest;
import com.ssibongee.daangnmarket.domain.entity.Category;
import com.ssibongee.daangnmarket.domain.entity.Member;
import com.ssibongee.daangnmarket.domain.entity.Post;
import com.ssibongee.daangnmarket.domain.repository.post.CategoryRepository;
import com.ssibongee.daangnmarket.domain.repository.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TradePostService implements PostService {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public void createNewPost(PostCreateRequest postCreateRequest, Member member) {

        Post post = postCreateRequest.toEntity(member);
        Category category = categoryRepository.findCategoryByCategoryName(
                postCreateRequest.getCategory()).orElseThrow (() -> new CategoryNotFoundException(postCreateRequest.getCategory()));

        post.setCategory(category);

        postRepository.save(post);
    }
}
