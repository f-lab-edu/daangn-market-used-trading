package com.ssibongee.daangnmarket.member.domain.repository;

import com.ssibongee.daangnmarket.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    public boolean existsByEmail(String email);

    public Optional<Member> findMemberByEmail(String email);

    public Optional<Member> findMemberById(long id);
}
