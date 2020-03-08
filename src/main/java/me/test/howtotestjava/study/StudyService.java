package me.test.howtotestjava.study;

import me.test.howtotestjava.domain.Member;
import me.test.howtotestjava.domain.Study;
import me.test.howtotestjava.member.MemberService;

import java.util.Optional;

public class StudyService {

    private final MemberService memberService;

    private final StudyRepository repository;

    public StudyService(MemberService memberService, StudyRepository repository){
        assert memberService != null;
        assert repository != null;

        this.memberService = memberService;
        this.repository = repository;
    }

    public Study createNewStudy(String memberId, Study study){
        Optional<Member> member = memberService.findById(memberId);
//        if(member.isPresent()){
//            throw new IllegalArgumentException("Member doesn't exist for id :" + member.getId());
//        }

        study.setOwner(member.orElseThrow(() -> new IllegalArgumentException("Member doesn't exist for id : " + member.get().getId())));


        Study storedStudy = repository.save(study);
        memberService.notify(storedStudy);

        return storedStudy;
    }
}
