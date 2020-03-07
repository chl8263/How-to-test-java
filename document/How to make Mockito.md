## Mockito

Mockito is one of test way that provide to make Mock Object and manage and validation.

- Mock : An Object that behaves like a real object but manage its behavior directly by the programmer.
 
There are example code like below.

The `createNewStudy` method is just get `Member` from `MemberService` and save at `StudyRepository`.

~~~java
public class StudyService {

    private final MemberService memberService;

    private final StudyRepository repository;

    public StudyService(MemberService memberService, StudyRepository repository){
        assert memberService != null;
        assert repository != null;

        this.memberService = memberService;
        this.repository = repository;
    }

    public Study createNewStudy(Long memberId, Study study){
        Optional<Member> member = memberService.findById(memberId);
        study.setOwner(member.orElseThrow(() -> new IllegalArgumentException("Member doesn't exist for id : " + member.get().getId())));
        return repository.save(study);
    }
}
~~~

First of all, make test code for this code for get `StudyService`.

First make `StudyService` and need to put `MemberService`, `StudyRepository` in constructor like below code. 

~~~java
public class StudyServiceTest {

    @Test
    void createStudyService(){
        
        MemberService memberService = new MemberService{
           ....
        
        StudyRepository studyRepository = new StudyRepository{
               
           ....

        StudyService studyService = new StudyService(memberService, studyRepository);

        assertNotNull(studyService);
    }
}
~~~

Above code is uncomfortable because you have to implement `MemberService` and `StudyRepository`. Therefore code will be long and code visibility will be bad too.

Using Mockito can solve this problem.

Can make Mock Object as use `@Mock` annotation and use `@ExtendWith(MockitoExtension.class)`. Then code will be shorter than before.  

~~~java
@ExtendWith(MockitoExtension.class)
public class StudyServiceTest {

    @Mock
    MemberService memberService;

    @Mock
    StudyRepository studyRepository;

    @Test
    void createStudyService(){

        StudyService studyService = new StudyService(memberService, studyRepository);

        assertNotNull(studyService);
    }
}
~~~

If want to just use `MemberService`, `StudyRepository` method in createStudyService test, can inject in parameter.

~~~java
@ExtendWith(MockitoExtension.class)
public class StudyServiceTest {

    @Test
    void createStudyService(@Mock MemberService memberService, @Mock StudyRepository studyRepository){

        StudyService studyService = new StudyService(memberService, studyRepository);

        assertNotNull(studyService);
    }
}
~~~