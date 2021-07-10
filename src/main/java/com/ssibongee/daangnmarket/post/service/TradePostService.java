package com.ssibongee.daangnmarket.post.service;

import com.ssibongee.daangnmarket.member.exception.UnAuthorizedAccessException;
import com.ssibongee.daangnmarket.post.exception.PostNotFoundException;
import com.ssibongee.daangnmarket.commons.annotation.AreaInfoRequired;
import com.ssibongee.daangnmarket.post.dto.PostRequest;
import com.ssibongee.daangnmarket.post.domain.entity.Category;
import com.ssibongee.daangnmarket.member.domain.entity.Member;
import com.ssibongee.daangnmarket.post.domain.entity.Post;
import com.ssibongee.daangnmarket.post.domain.repository.PostRepository;
import com.ssibongee.daangnmarket.member.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.ssibongee.daangnmarket.commons.config.CacheKeyConfig.POST;

@Service
@RequiredArgsConstructor
public class TradePostService implements PostService {

    private final PostRepository postRepository;
    private final CategoryService categoryService;
    private final LoginService loginService;


    @Override
    @AreaInfoRequired
    @Transactional
    @Caching(evict = {
            @CacheEvict(
                    key = "#member.getAddress().state + '.' + #member.getAddress().city + '.' + #member.getAddress().town",
                    value = POST
            ),
            @CacheEvict(
                    key = "#member.getAddress().state + '.' + #member.getAddress().city + '.' + #member.getAddress().town + '.' + #postRequest.category",
                    value = POST
            )
    })
    public void createNewPost(PostRequest postRequest, Member member) {

        Post post = postRequest.toEntity(member);
        Category category = categoryService.findCategoryByName(postRequest.getCategory());

        post.setCategory(category);

        postRepository.save(post);
    }

    @Override
    @Transactional(readOnly = true)
    public Post findPostById(Long postId) {
        return postRepository.findPostById(postId).orElseThrow(PostNotFoundException::new);
    }

    @Override
    @Transactional
    @Caching(evict = {
            @CacheEvict(
                    key = "#member.getAddress().state + '.' + #member.getAddress().city + '.' + #member.getAddress().town",
                    value = POST
            ),
            @CacheEvict(
                    key = "#member.getAddress().state + '.' + #member.getAddress().city + '.' + #member.getAddress().town + '.' + #postRequest.category",
                    value = POST
            )
    })
    public void updatePost(Post post, PostRequest postRequest) {

        if (isMatchedAuthor(post)) {
            Category category = categoryService.findCategoryByName(postRequest.getCategory());

            post.updatePost(postRequest);
            post.setCategory(category);
        }
    }

    @Override
    @Transactional
    @Caching(evict = {
            @CacheEvict(
                    key = "#member.getAddress().state + '.' + #member.getAddress().city + '.' + #member.getAddress().town",
                    value = POST
            ),
            @CacheEvict(
                    key = "#member.getAddress().state + '.' + #member.getAddress().city + '.' + #member.getAddress().town + '.' + #postRequest.category",
                    value = POST
            )
    })
    public void removePost(Post post) {

        if (isMatchedAuthor(post)) {
            post.removePost();
        }
    }

    @Override
    public boolean isMatchedAuthor(Post post) {

        Member member = loginService.getLoginMember();

        if (post.getAuthor() != member) {
            throw new UnAuthorizedAccessException();
        }

        return true;
    }

}
