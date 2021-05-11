package com.ssibongee.daangnmarket.post.service;

import com.ssibongee.daangnmarket.member.domain.entity.Member;
import com.ssibongee.daangnmarket.post.dto.AddressRequest;
import com.ssibongee.daangnmarket.post.dto.PostPageResponse;
import org.springframework.data.domain.Pageable;

public interface PostSearchService {

    public PostPageResponse findAllByMemberAddress(Member member, Pageable pageable);

    public PostPageResponse findAllByAddress(AddressRequest address, Pageable pageable);
}
