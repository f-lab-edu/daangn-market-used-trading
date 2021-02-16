package com.ssibongee.daangnmarket.domain.dto;

import com.ssibongee.daangnmarket.domain.entity.Member;
import com.ssibongee.daangnmarket.domain.entity.Post;
import com.ssibongee.daangnmarket.domain.entity.TradeStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostRequest {

    @NotEmpty
    @Length(max = 100, message = "제목은 최대 100글자를 넘을 수 없습니다.")
    private String title;

    @NotEmpty
    private String content;

    @NotEmpty
    private String category;

    public Post toEntity(Member member) {
        return Post.builder()
                .title(this.title)
                .content(this.content)
                .author(member)
                .address(member.getAddress())
                .location(member.getLocation())
                .status(TradeStatus.SALE)
                .build();
    }

}
