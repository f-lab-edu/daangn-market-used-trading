package com.ssibongee.daangnmarket.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "MEMBER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    private String email;

    private String password;

    private String nickname;

    @Builder
    public Member(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    public void update(String nickname) {
        this.nickname = nickname;
    }

}
