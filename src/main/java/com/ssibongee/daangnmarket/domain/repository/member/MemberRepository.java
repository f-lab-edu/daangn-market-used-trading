package com.ssibongee.daangnmarket.domain.repository.member;

import com.ssibongee.daangnmarket.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    public boolean existsByEmail(String email);

    public Optional<Member> findMemberByEmail(String email);
}
