package com.ssibongee.daangnmarket.post.service;

import com.ssibongee.daangnmarket.commons.annotation.AreaInfoRequired;
import com.ssibongee.daangnmarket.member.domain.entity.Member;
import com.ssibongee.daangnmarket.post.domain.entity.Address;
import com.ssibongee.daangnmarket.post.domain.entity.Post;
import com.ssibongee.daangnmarket.post.domain.repository.PostSearchRepository;
import com.ssibongee.daangnmarket.post.dto.AddressRequest;
import com.ssibongee.daangnmarket.post.dto.PostPageResponse;
import com.ssibongee.daangnmarket.post.dto.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TradePostSearchService implements PostSearchService {

    private final PostSearchRepository postSearchRepository;

    @Override
    @AreaInfoRequired
    public PostPageResponse findAllByMemberAddress(Member member, Pageable pageable) {

        return getPagePostResponse(member.getAddress(), pageable);
    }

    @Override
    public PostPageResponse findAllByAddress(AddressRequest address, Pageable pageable) {

        return getPagePostResponse(AddressRequest.toEntity(address), pageable);
    }

    private PostPageResponse getPagePostResponse(Address address, Pageable pageable) {

        Page<Post> posts = postSearchRepository.findAllByMemberAddress(address.getState(), address.getCity(), address.getTown(), pageable);

        List<PostResponse> postResponses = posts.getContent().stream().map(PostResponse::of).collect(Collectors.toList());

        return PostPageResponse.builder()
                .totalPage(posts.getTotalPages())
                .currentPage(pageable.getPageNumber())
                .postResponses(postResponses)
                .build();
    }
}
