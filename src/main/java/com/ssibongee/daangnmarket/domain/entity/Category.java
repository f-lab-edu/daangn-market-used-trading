package com.ssibongee.daangnmarket.domain.entity;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_ID")
    private Long id;

    private String categoryName;


}
