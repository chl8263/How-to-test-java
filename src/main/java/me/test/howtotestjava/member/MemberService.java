package me.test.howtotestjava.member;

import me.test.howtotestjava.domain.Member;

import java.util.Optional;

public interface MemberService {

    Optional<Member> findById(String memberId) throws MemberNotFoundException;

    void validate(String memberId);
}
