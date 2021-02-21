package com.ssibongee.daangnmarket.post.controller;


import com.ssibongee.daangnmarket.commons.annotation.LoginMember;
import com.ssibongee.daangnmarket.commons.annotation.LoginRequired;
import com.ssibongee.daangnmarket.post.dto.PostRequest;
import com.ssibongee.daangnmarket.post.dto.PostResponse;
import com.ssibongee.daangnmarket.member.domain.entity.Member;
import com.ssibongee.daangnmarket.post.domain.entity.Post;
import com.ssibongee.daangnmarket.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.ssibongee.daangnmarket.commons.HttpStatusResponseEntity.RESPONSE_OK;
import static com.ssibongee.daangnmarket.commons.HttpStatusResponseEntity.RESPONSE_UNAUTHORIZED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @LoginRequired
    @PostMapping
    public ResponseEntity<HttpStatus> createPost(@RequestBody @Valid PostRequest postRequest,
                                                 @LoginMember Member member) {

        postService.createNewPost(postRequest, member);

        return RESPONSE_OK;
    }

    @LoginRequired
    @GetMapping("/{postId}")
    public ResponseEntity<PostResponse> findPost(@PathVariable Long postId) {

        return ResponseEntity.ok(PostResponse.of(postService.findPostById(postId)));
    }

    @LoginRequired
    @PutMapping("/{postId}")
    public ResponseEntity<HttpStatus> updatePost(@Valid @RequestBody PostRequest postRequest,
                                                 @PathVariable Long postId) {

        Post post = postService.findPostById(postId);

        if(postService.updatePost(post, postRequest)) {
            return RESPONSE_OK;
        }

        return RESPONSE_UNAUTHORIZED;
    }

    @LoginRequired
    @DeleteMapping("/{postId}")
    public ResponseEntity<HttpStatus> deletePost(@PathVariable Long postId) {

        Post post = postService.findPostById(postId);

        if(postService.removePost(post)) {
            return RESPONSE_OK;
        }

        return RESPONSE_UNAUTHORIZED;
    }
}
