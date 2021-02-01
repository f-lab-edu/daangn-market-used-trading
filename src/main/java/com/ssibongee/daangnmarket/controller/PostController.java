package com.ssibongee.daangnmarket.controller;


import com.ssibongee.daangnmarket.commons.annotation.LoginMember;
import com.ssibongee.daangnmarket.commons.annotation.LoginRequired;
import com.ssibongee.daangnmarket.domain.dto.PostCreateRequest;
import com.ssibongee.daangnmarket.domain.entity.Member;
import com.ssibongee.daangnmarket.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.ssibongee.daangnmarket.commons.HttpStatusResponseEntity.RESPONSE_OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @LoginRequired
    @PostMapping
    public ResponseEntity<HttpStatus> createNewPost(@RequestBody @Valid PostCreateRequest postCreateRequest,
                                                    @LoginMember Member member) {

        postService.createNewPost(postCreateRequest, member);

        return RESPONSE_OK;
    }

}
