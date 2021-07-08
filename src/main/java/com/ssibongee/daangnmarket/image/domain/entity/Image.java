package com.ssibongee.daangnmarket.image.domain.entity;

import com.ssibongee.daangnmarket.commons.BaseTimeEntity;
import com.ssibongee.daangnmarket.post.domain.entity.Post;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Image extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IMAGE_ID")
    private Long id;

    @Column(name = "IMAGE_NAME")
    private String name;

    @Column(name = "IMAGE_URL")
    private String url;

    @Column(name = "IS_REMOVED")
    private boolean removed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    private Post post;

    @Builder
    public Image(String name, String url, Post post) {
        this.name = name;
        this.url = url;
        this.post = post;
    }

}
