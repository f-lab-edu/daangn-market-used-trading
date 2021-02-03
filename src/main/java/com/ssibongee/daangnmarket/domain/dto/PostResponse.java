package com.ssibongee.daangnmarket.domain.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.ssibongee.daangnmarket.domain.entity.Address;
import com.ssibongee.daangnmarket.domain.entity.Location;
import com.ssibongee.daangnmarket.domain.entity.Post;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {

    private Long id;
    private String title;
    private String author;
    private String email;
    private String content;

    private String status;
    private String category;

    private Address address;
    private Location location;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createdTime;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime modifiedTime;


    public static PostResponse of(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .author(post.getAuthor().getNickname())
                .email(post.getAuthor().getEmail())
                .content(post.getContent())
                .createdTime(post.getCreatedTime())
                .modifiedTime(post.getModifiedTime())
                .status(post.getStatus().getTradeStatus())
                .category(post.getCategory().getCategoryName())
                .address(post.getAddress())
                .location(post.getLocation())
                .build();
    }

}
