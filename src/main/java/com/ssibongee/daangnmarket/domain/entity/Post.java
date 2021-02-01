package com.ssibongee.daangnmarket.domain.entity;

import com.ssibongee.daangnmarket.commons.BaseTimeEntity;
import lombok.Builder;

import javax.persistence.*;

@Entity
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "POST_ID")
    private Long id;

    private String title;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @Enumerated(EnumType.STRING)
    private TradeStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member author;

    @Lob
    private String content;

    @Embedded
    private Address address;

    @Embedded
    private Location location;

    @Builder
    public Post(String title, TradeStatus status, Member author,
                String content, Address address, Location location) {
        this.title = title;
        this.status = status;
        this.author = author;
        this.content = content;
        this.address = address;
        this.location = location;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
