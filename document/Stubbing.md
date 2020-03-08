## Stubbing

Stubbing is define Object behavior.

Here is official document [How about some stubbing](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html#2). 


There are few feature in Stubbing.

- Default return value is NULL(Optional.empty return when use Optional).
- Primitive type return basic Primitive type.
- Collection return empty Collection.
- Nothing happen when use `void` method.

If you want define behavior, first use `when` like below code. The below code show when input `"Ewan"` in `findById`, then return `Optional.of(member)`. 

~~~java
when(memberService.findById("Ewan")).thenReturn(Optional.of(member));
~~~

Any happen occurred yet, you define just one of object behavior.

Now show below code. Below code has `Member` Object and `memberService` call `findById` method.

Now can validate using assertEquals, the test will be passed.

~~~java
@Test
void createStudyService(@Mock MemberService memberService, @Mock StudyRepository studyRepository){
    
    StudyService studyService = new StudyService(memberService, studyRepository);

    Member member = new Member();
    member.setId("Ewan");
    member.setEmail("Ewan123@gmail.com");

    when(memberService.findById("Ewan")).thenReturn(Optional.of(member));

    Optional<Member> member = memberService.findById("Ewan");                                            
    assertEquals("Ewan123@gmail.com", member.get().getEmail());
}
~~~

If want test about any parameter, can use `any()` at parameter.

~~~java
when(memberService.findById(any())).thenReturn(Optional.of(member));
~~~

If want make stub for Exception about void method, define use doThrow.

~~~java
doThrow(IllegalArgumentException.class).when(memberService).validate("Ewan");
~~~

### verify
Also you can verify how many time call sp specific method.
~~~java
verify(memberService, times(1)).notify(study);
~~~
