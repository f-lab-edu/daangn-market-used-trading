package com.ssibongee.daangnmarket.domain.repository.member;

import com.ssibongee.daangnmarket.domain.entity.member.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    public boolean existsByEmail(String email);
}
