package me.test.howtotestjava.member;

import me.test.howtotestjava.domain.Member;

import java.util.Optional;

public interface MemberService {

    Optional<Member> findById(Long memberId) throws MemberNotFoundException;
}
