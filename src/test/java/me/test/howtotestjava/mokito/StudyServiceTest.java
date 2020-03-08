package me.test.howtotestjava.mokito;


import me.test.howtotestjava.domain.Member;
import me.test.howtotestjava.domain.Study;
import me.test.howtotestjava.member.MemberNotFoundException;
import me.test.howtotestjava.member.MemberService;
import me.test.howtotestjava.study.StudyRepository;
import me.test.howtotestjava.study.StudyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudyServiceTest {

//    @Mock
//    MemberService memberService;
//
//    @Mock
//    StudyRepository studyRepository;

    @Test
    void exeStubbing (@Mock MemberService memberService, @Mock StudyRepository studyRepository){

        Member member = new Member();
        member.setId("Ewan");
        member.setEmail("Ewan123@gmail.com");

        Study study = new Study(10, "Ewan");

        when(memberService.findById("Ewan")).thenReturn(Optional.of(member));

        assertNotNull(memberService.findById("Ewan"));

        when(studyRepository.save(study)).thenReturn(study);

        assertEquals("Ewan", studyRepository.save(study).getName());
    }

    @Test
    void createStudyService(@Mock MemberService memberService, @Mock StudyRepository studyRepository){

        //StudyService studyService = new StudyService(memberService, studyRepository);

        //MemberService memberService = mock(MemberService.class);
        //StudyRepository studyRepository = mock(StudyRepository.class);

        //Optional<Member> optional = memberService.findById(1L);
        //assertNull(optional);

        StudyService studyService = new StudyService(memberService, studyRepository);

        Member member = new Member();
        member.setId("Ewan");
        member.setEmail("Ewan123@gmail.com");

        //when(memberService.findById("Ewan")).thenReturn(Optional.of(member));
        when(memberService.findById(any())).thenReturn(Optional.of(member));

        Study study = new Study(10, "java");

        Optional<Member> member1 = memberService.findById("Ewan2");
        assertEquals("Ewan123@gmail.com", member1.get().getEmail());

        studyService.createNewStudy("Ewan", study);

        assertNotNull(studyService);

        doThrow(IllegalArgumentException.class).when(memberService).validate("Ewan");
        assertThrows(IllegalArgumentException.class, () -> memberService.validate("Ewan"));

        memberService.validate("Ewan2");
        //when(memberService.findById("Ewan")).thenThrow(new RuntimeException());

        when(memberService.findById("Ewan"))
                .thenReturn(Optional.of(member))
                .thenThrow(new RuntimeException())
                .thenReturn(Optional.empty());

        Optional<Member> member2 = memberService.findById("Ewan");

        assertEquals("Ewan123@gmail.com", member2.get().getEmail());

        assertThrows(RuntimeException.class, () -> {
            memberService.findById("Ewan");
        });

        assertEquals(Optional.empty(), memberService.findById("Ewan"));

        memberService.notify(study);
        verify(memberService, times(1)).notify(study);
        //verify(memberService, never()).validate(any());
    }
}
