package com.ssibongee.daangnmarket.domain.repository.member;

import com.ssibongee.daangnmarket.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    public boolean existsByEmail(String email);
}
