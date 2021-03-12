package com.ssibongee.daangnmarket.fixture;

import com.ssibongee.daangnmarket.member.domain.entity.Member;
import com.ssibongee.daangnmarket.member.dto.MemberDto;

public class MemberFixture {

    public static final Member ADMIN_MEMBER = new Member(
            "admin@daangnmarket.com",
            "!Admin123",
            "admin"
    );

    public static final Member MEMBER1 = new Member(
            "member1@daangnmarket.com",
            "!Member123",
            "user1"
    );

    public static final Member MEMBER2 = new Member(
            "member2@daangnmarket.com",
            "!Member123",
            "user2"
    );

    public static final MemberDto MEMBER_REGISTRATION_REQUEST = new MemberDto(
            "newmember@daangnmarket.com",
            "!Newmember123",
            "newMember"
    );

    public static final Member NEW_MEMBER = new Member(
            "newmember@daangnmarket.com",
            "!Newmember123",
            "newMember"
    );

    public static final String UNIQUE_MEMBER_EMAIL = "unique@daangnmarket.com";

    public static final String DUPLICATED_MEMBER_EMAIL = "duplicated@daangnmarket.com";

}
