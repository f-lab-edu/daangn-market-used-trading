package com.ssibongee.daangnmarket.post.controller;

import com.ssibongee.daangnmarket.commons.annotation.LoginMember;
import com.ssibongee.daangnmarket.commons.annotation.LoginRequired;
import com.ssibongee.daangnmarket.member.domain.entity.Member;
import com.ssibongee.daangnmarket.post.dto.AddressRequest;
import com.ssibongee.daangnmarket.post.dto.PostPageResponse;
import com.ssibongee.daangnmarket.post.service.TradePostSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/searches")
public class PostSearchController {

    private final TradePostSearchService tradePostSearchService;

    @LoginRequired
    @GetMapping
    public ResponseEntity<PostPageResponse> getTradePosts(@LoginMember Member member, Pageable pageable) {

        PostPageResponse page = tradePostSearchService.findAllByMemberAddress(member, pageable);

        return ResponseEntity.ok(page);
    }

    @LoginRequired
    @GetMapping("/address")
    public ResponseEntity<PostPageResponse> getTradePostsByAddress(@Valid AddressRequest address, Pageable pageable) {

        PostPageResponse page = tradePostSearchService.findAllByAddress(address, pageable);

        return ResponseEntity.ok(page);
    }
}
