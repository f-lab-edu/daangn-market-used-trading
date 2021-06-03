package com.ssibongee.daangnmarket.post.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PostPageResponse {

    private int totalPage;
    private int currentPage;
    private List<PostResponse> postResponses = new ArrayList<>();

    @Builder
    public PostPageResponse(int totalPage, int currentPage, List<PostResponse> postResponses) {
        this.totalPage = totalPage;
        this.currentPage = currentPage;
        this.postResponses = postResponses;
    }
}
