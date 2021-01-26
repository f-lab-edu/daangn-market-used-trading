package com.ssibongee.daangnmarket.domain.entity;

import com.ssibongee.daangnmarket.commons.BaseTimeEntity;

import javax.persistence.*;

@Entity
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "POST_ID")
    private Long id;

    private String title;

    @Enumerated(EnumType.STRING)
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

}
